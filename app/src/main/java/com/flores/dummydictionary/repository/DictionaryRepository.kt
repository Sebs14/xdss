package com.flores.dummydictionary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flores.dummydictionary.data.DummyDictionaryDatabase
import com.flores.dummydictionary.data.dao.AntonymDao
import com.flores.dummydictionary.data.dao.SynonymDao
import com.flores.dummydictionary.data.dao.WordDao
import com.flores.dummydictionary.data.model.Word
import com.flores.dummydictionary.data.network.ApiResponse
import com.flores.dummydictionary.data.network.WordService
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository(

    database: DummyDictionaryDatabase,
    private val api: WordService,
) {

    private val wordDoa = database.wordDao()
    suspend fun getAllWords(): ApiResponse<LiveData<List<Word>>> {
     return try {
         val response = api.getAllWord()
         if (response.count > 0) {
             wordDoa.insertWord(response.words[0])
         }
         ApiResponse.Success(data = wordDoa.getAllWords())
     }  catch (e: HttpException) {
         ApiResponse.Error(exception = e)
     } catch (e: IOException) {
         ApiResponse.Error(exception = e)
     }

    }

    suspend fun addWord(word: Word) {
        wordDoa.insertWord(word)
    }
}