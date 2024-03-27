package mikhan.nik.new_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mikhan.nik.data.NoteItem
import mikhan.nik.data.NoteRepository
import mikhan.nik.datastore.DataStoreManager
import mikhan.nik.utils.UiEvent
import mikhan.nik.utils.getCurrentTime
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle,
    dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent> ()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var noteId = -1
    private var noteItem: NoteItem? = null

    var titleColor = mutableStateOf("#487242")

    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt() ?: -1
        if (noteId != -1) {
            viewModelScope.launch {
                repository.getNoteItemById(noteId).let { noteItem ->
                    title = noteItem.title
                    description = noteItem.description
                    this@NewNoteViewModel.noteItem = noteItem
                }
                dataStoreManager.getStringPreference(
                    DataStoreManager.TITLE_COLOR,
                    "#487242"
                ).collect { color ->
                    titleColor.value = color
                }
            }
        }
    }

    fun onEvent(event: NewNoteEvent) {
        when(event) {
            is NewNoteEvent.OnSave -> {
                viewModelScope.launch{
                    if (title.isEmpty()){
                        sendUiEvent(UiEvent.ShowSnackBar(
                            "Заголовок не может быть пустым!"
                        ))
                        return@launch
                    }
                    repository.insertItem(
                        NoteItem(
                            noteItem?.id,
                            title,
                            description,
                            noteItem?.time ?: getCurrentTime(),
                        )
                    )
                }
                sendUiEvent(UiEvent.PopBackStack)
            }
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
            }
            is NewNoteEvent.OnDescriptionChange -> {
                description = event.description
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }
}