package it.rokettoapp.roketto.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.ArticleEvent;
import it.rokettoapp.roketto.model.ArticleLaunch;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.LauncherConfig;
import it.rokettoapp.roketto.model.MissionPatch;
import it.rokettoapp.roketto.model.SpacecraftConfiguration;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.model.Update;

public class Converters {
    //date
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    //LauncherConfig
    @TypeConverter
    public static String fromLaunchConfToJson(List<LauncherConfig> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<String> ids = new ArrayList<String>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<LauncherConfig> fromJsonToLaunchConf(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> ids = gson.fromJson(value, type);
        List<LauncherConfig> valueList = new ArrayList<LauncherConfig>();
        for (int i=0; i<ids.size(); i++){
            LauncherConfig launcherConfigId = new LauncherConfig(ids.get(i));
            valueList.add(launcherConfigId);
        }
        return valueList;
    }

    //SpacecraftConfiguration
    @TypeConverter
    public static String fromSpaceConfToJson(List<SpacecraftConfiguration> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<Integer>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<SpacecraftConfiguration> fromJsonToSpaceConf(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<SpacecraftConfiguration> valueList = new ArrayList<SpacecraftConfiguration>();
        for (int i=0; i<ids.size(); i++){
            SpacecraftConfiguration spaceConfId = new SpacecraftConfiguration(ids.get(i));
            valueList.add(spaceConfId);
        }
        return valueList;
    }

    //ArticleLaunch
    @TypeConverter
    public static String fromArticleLaunchToJson(List<ArticleLaunch> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<String> ids = new ArrayList<String>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<ArticleLaunch> fromJsonToArticleLaunch(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> ids = gson.fromJson(value, type);
        List<ArticleLaunch> valueList = new ArrayList<ArticleLaunch>();
        for (int i=0; i<ids.size(); i++){
            ArticleLaunch articleLaunchId = new ArticleLaunch(ids.get(i));
            valueList.add(articleLaunchId);
        }
        return valueList;
    }

    //ArticleEvent
    @TypeConverter
    public static String fromArticleEventToJson(List<ArticleEvent> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<Integer>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<ArticleEvent> fromJsonToArticleEvent(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<ArticleEvent> valueList = new ArrayList<ArticleEvent>();
        for (int i=0; i<ids.size(); i++){
            ArticleEvent articleEventId = new ArticleEvent(ids.get(i));
            valueList.add(articleEventId);
        }
        return valueList;
    }

    //SpacecraftFlight
    @TypeConverter
    public static String fromSpaceFlightToJson(List<SpacecraftFlight> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<Integer>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<SpacecraftFlight> fromJsonToSpaceFlight(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<SpacecraftFlight> valueList = new ArrayList<SpacecraftFlight>();
        for (int i=0; i<ids.size(); i++){
            SpacecraftFlight spacecraftFlightId = new SpacecraftFlight(ids.get(i));
            valueList.add(spacecraftFlightId);
        }
        return valueList;
    }

    //Update
    @TypeConverter
    public static String fromUpdateToJson(List<Update> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<Integer>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<Update> fromJsonToUpdate(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<Update> valueList = new ArrayList<Update>();
        for (int i=0; i<ids.size(); i++){
            Update updateId = new Update(ids.get(i));
            valueList.add(updateId);
        }
        return valueList;
    }

    //Agency
    @TypeConverter
    public static String fromAgencyToJson(List<Agency> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<Integer>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<Agency> fromJsonToAgency(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<Agency> valueList = new ArrayList<Agency>();
        for (int i=0; i<ids.size(); i++){
            Agency agencyId = new Agency(ids.get(i));
            valueList.add(agencyId);
        }
        return valueList;
    }

    //MissionPatch
    @TypeConverter
    public static String fromMissionPatchToJson(List<MissionPatch> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<String> ids = new ArrayList<String>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<MissionPatch> fromJsonToMissionPatch(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> ids = gson.fromJson(value, type);
        List<MissionPatch> valueList = new ArrayList<MissionPatch>();
        for (int i=0; i<ids.size(); i++){
            MissionPatch missionPatchId = new MissionPatch(ids.get(i));
            valueList.add(missionPatchId);
        }
        return valueList;
    }

    //Launch
    @TypeConverter
    public static String fromLaunchToJson(List<Launch> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<String> ids = new ArrayList<String>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        String json = gson.toJson(ids);
        return json;
    }
    @TypeConverter
    public static List<Launch> fromJsonToLaunch(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> ids = gson.fromJson(value, type);
        List<Launch> valueList = new ArrayList<Launch>();
        for (int i=0; i<ids.size(); i++){
            Launch launchId = new Launch(ids.get(i));
            valueList.add(launchId);
        }
        return valueList;
    }
}