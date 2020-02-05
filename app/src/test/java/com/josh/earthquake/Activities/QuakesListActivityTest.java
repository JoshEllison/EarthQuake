package com.josh.earthquake.Activities;

import com.android.volley.toolbox.JsonObjectRequest;
import com.josh.earthquake.Util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class QuakesListActivityTest {


    private QuakesListActivity quakesListActivity = spy(new QuakesListActivity());

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*
        This tests passing in an empty URL string
     */
    @Test
    public void sendRequestEmptyTest(){

        // Setup
        JsonObjectRequest mockJson = mock(JsonObjectRequest.class);
        mockJson.setSequence(1);

        // Act
        doReturn(mockJson).when(quakesListActivity).sendRequest(anyString());
        JsonObjectRequest result = quakesListActivity.sendRequest("");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getSequence());
    }

    @Test
    public void createAdapter() {

        // Setup

        // Act

        // Assert
    }

    @Test
    public void parseJsonNullTest() {

        // Assert
        assertNotNull(quakesListActivity.parseJson(null));
    }

    @Test
    public void parseJsonEmptyTest() {


        JSONArray jsonArray = new JSONArray();
        // Assert
        assertNotNull(quakesListActivity.parseJson(jsonArray));
    }

    @Test
    public void parseJsonValidTest() {

        // Setup
        JSONArray jsonArray = new JSONArray();


        // Assert

    }
}
