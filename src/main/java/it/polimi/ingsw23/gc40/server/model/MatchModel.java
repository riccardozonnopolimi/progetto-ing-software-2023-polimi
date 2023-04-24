package it.polimi.ingsw23.gc40.server.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw23.gc40.server.model.goals.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Observable;


public class MatchModel extends Observable implements Remote, Serializable {
}