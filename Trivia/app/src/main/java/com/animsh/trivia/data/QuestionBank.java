package com.animsh.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.animsh.trivia.controller.AppController;
import com.animsh.trivia.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private ArrayList<Question> questionArrayList = new ArrayList<>();
    private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(final AnswerListAsyncResponse callback) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSON ARRAY: ", "onResponse: " + response);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // add data to question object
                                Question question = new Question();
                                question.setAnswer(response.getJSONArray(i).get(0).toString());
                                question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));

                                // add question to list
                                questionArrayList.add(question);
                                Log.d("JSON: ", "onResponse: " + question);

                                //Log.d("JSON QUESTION: ", "onResponse: " + response.getJSONArray(i).get(0));
                                //Log.d("JSON ANSWER: ", "onResponse: " + response.getJSONArray(i).getBoolean(1));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (null != callback) callback.processFinished(questionArrayList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
