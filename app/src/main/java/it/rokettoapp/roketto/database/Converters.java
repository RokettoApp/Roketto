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
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.DockingLocation;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.FirstStage;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.LauncherConfig;
import it.rokettoapp.roketto.model.Location;
import it.rokettoapp.roketto.model.MissionPatch;
import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.model.SpacecraftConfiguration;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.model.Update;
import it.rokettoapp.roketto.model.Url;

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

        if (value == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<LauncherConfig>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public static List<LauncherConfig> fromJsonToLaunchConfList(String value) {

        if (value == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<LauncherConfig>>() {}.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public static String fromLauncherConfigToJson(LauncherConfig launcherConfig) {

        if (launcherConfig == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<LauncherConfig>() {}.getType();
        return gson.toJson(launcherConfig, type);
    }

    @TypeConverter
    public static LauncherConfig fromJsonToLauncherConfig(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<LauncherConfig>() {}.getType();
        return gson.fromJson(json, type);
    }

    //SpacecraftConfiguration
    @TypeConverter
    public static String fromSpaceConfToJson(List<SpacecraftConfiguration> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<SpacecraftConfiguration> fromJsonToSpaceConf(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<SpacecraftConfiguration> valueList = new ArrayList<>();
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
        List<String> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<ArticleLaunch> fromJsonToArticleLaunch(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> ids = gson.fromJson(value, type);
        List<ArticleLaunch> valueList = new ArrayList<>();
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
        List<Integer> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<ArticleEvent> fromJsonToArticleEvent(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<ArticleEvent> valueList = new ArrayList<>();
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
        List<Integer> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<SpacecraftFlight> fromJsonToSpaceFlight(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<SpacecraftFlight> valueList = new ArrayList<>();
        for (int i=0; i<ids.size(); i++){
            SpacecraftFlight spacecraftFlightId = new SpacecraftFlight(ids.get(i));
            valueList.add(spacecraftFlightId);
        }
        return valueList;
    }

    @TypeConverter
    public static Integer fromSpacecraftFlightToInteger(SpacecraftFlight spacecraftFlight) {

        if (spacecraftFlight == null) return null;

        return spacecraftFlight.getId();
    }

    @TypeConverter
    public static SpacecraftFlight fromIntegerToSpacecaraftFlight(Integer id) {

        if (id == null) return null;

        return new SpacecraftFlight(id);
    }

    //Update
    @TypeConverter
    public static String fromUpdateToJson(List<Update> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<Update> fromJsonToUpdate(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<Update> valueList = new ArrayList<>();
        for (int i=0; i<ids.size(); i++){
            Update updateId = new Update(ids.get(i));
            valueList.add(updateId);
        }
        return valueList;
    }

    //Agency
    @TypeConverter
    public static String fromAgencyListToJson(List<Agency> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<Agency> fromJsonToAgencyList(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> ids = gson.fromJson(value, type);
        List<Agency> valueList = new ArrayList<>();
        for (int i=0; i<ids.size(); i++){
            Agency agencyId = new Agency(ids.get(i));
            valueList.add(agencyId);
        }
        return valueList;
    }

    @TypeConverter
    public static Integer fromAgencyToInteger(Agency agency) {

        if (agency == null) return null;
        return agency.getId();
    }

    @TypeConverter
    public static Agency fromIntegerToAgency(Integer id) {

        if (id == null) return null;
        return new Agency(id);
    }

    //MissionPatch
    @TypeConverter
    public static String fromMissionPatchToJson(List<MissionPatch> value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        List<String> ids = new ArrayList<>();
        for (int i=0; i<value.size(); i++){
            ids.add(value.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<MissionPatch> fromJsonToMissionPatch(String value) {
        if (value == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> ids = gson.fromJson(value, type);
        List<MissionPatch> valueList = new ArrayList<>();
        for (int i=0; i<ids.size(); i++){
            MissionPatch missionPatchId = new MissionPatch(ids.get(i));
            valueList.add(missionPatchId);
        }
        return valueList;
    }

    //Launch
    @TypeConverter
    public static String fromLaunchToJson(List<Launch> launchList) {

        if (launchList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Launch>>() {}.getType();
        List<Launch> minLaunchList = new ArrayList<>();
        for (Launch launch : launchList) {
            minLaunchList.add(Launch.buildMinLaunch(launch));
        }
        return gson.toJson(minLaunchList, type);
    }

    @TypeConverter
    public static List<Launch> fromJsonToLaunch(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Launch>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String fromLaunchToString(Launch launch) {

        if (launch == null) return null;

        return launch.getId();
    }

    @TypeConverter
    public static Launch fromStringToLaunch(String id) {

        if (id == null) return null;

        return new Launch(id);
    }

    // Url
    @TypeConverter
    public static String fromUrlListToJson(List<Url> urlList) {

        if (urlList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Url>>() {}.getType();
        return gson.toJson(urlList, type);
    }

    @TypeConverter
    public static List<Url> fromJsonToUrlList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Url>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Program
    @TypeConverter
    public static String fromProgramListToJson(List<Program> programList) {

        if (programList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Program>>() {}.getType();
        List<Program> minProgramList = new ArrayList<>();
        for (Program program : programList) {
            minProgramList.add(Program.buildMinProgram(program));
        }
        return gson.toJson(minProgramList, type);
    }

    @TypeConverter
    public static List<Program> fromJsonToProgramList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Program>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // DockingEvent
    @TypeConverter
    public static String fromDockingEventListToJson(List<DockingEvent> dockingEventList) {

        if (dockingEventList == null) return null;

        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < dockingEventList.size(); i++) {
            ids.add(dockingEventList.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<DockingEvent> fromJsonToDockingEventList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> idList = gson.fromJson(json, type);
        List<DockingEvent> dockingEventList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            DockingEvent dockingEvent = new DockingEvent(idList.get(i));
            dockingEventList.add(dockingEvent);
        }
        return dockingEventList;
    }

    // Spacecraft
    @TypeConverter
    public static Integer fromSpacecraftToInteger(Spacecraft spacecraft) {

        if (spacecraft == null) return null;

        return spacecraft.getId();
    }

    @TypeConverter
    public static Spacecraft fromIntegerToSpacecaraft(Integer id) {

        if (id == null) return null;

        return new Spacecraft(id);
    }

    // FirstStage
    @TypeConverter
    public static String fromFirstStageListToJson(List<FirstStage> firstStageList) {

        if (firstStageList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<AstronautFlight>>() {}.getType();
        return gson.toJson(firstStageList, type);
    }

    @TypeConverter
    public static List<FirstStage> fromJsonToFirstStageList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<AstronautFlight>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Spacecraft
    @TypeConverter
    public static Integer fromLocationToInteger(Location location) {

        if (location == null) return null;

        return location.getId();
    }

    @TypeConverter
    public static Location fromIntegerToLocation(Integer id) {

        if (id == null) return null;

        return new Location(id);
    }

    // Pad
    @TypeConverter
    public static String fromPadListToJson(List<Pad> padList) {

        if (padList == null) return null;

        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < padList.size(); i++) {
            ids.add(padList.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<Pad> fromJsonToPadList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> idList = gson.fromJson(json, type);
        List<Pad> padList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            Pad pad = new Pad(idList.get(i));
            padList.add(pad);
        }
        return padList;
    }

    @TypeConverter
    public static Integer fromPadToInteger(Pad pad) {

        if (pad == null) return null;

        return pad.getId();
    }

    @TypeConverter
    public static Pad fromIntegerToPad(Integer id) {

        if (id == null) return null;

        return new Pad(id);
    }

    // Expedition
    @TypeConverter
    public static String fromExpeditionListToJson(List<Expedition> expeditionList) {

        if (expeditionList == null) return null;

        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < expeditionList.size(); i++) {
            ids.add(expeditionList.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<Expedition> fromJsonToExpeditionList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> idList = gson.fromJson(json, type);
        List<Expedition> expeditionList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            Expedition expedition = new Expedition(idList.get(i));
            expeditionList.add(expedition);
        }
        return expeditionList;
    }

    // AstronautFlight
    @TypeConverter
    public static String fromAstronautFlightListToJson(List<AstronautFlight> astronautFlightList) {

        if (astronautFlightList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<AstronautFlight>>() {}.getType();
        return gson.toJson(astronautFlightList, type);
    }

    @TypeConverter
    public static List<AstronautFlight> fromJsonToAstronautFlightList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<AstronautFlight>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Astronaut
    @TypeConverter
    public static String fromAstronautListToJson(List<Astronaut> astronautList) {

        if (astronautList == null) return null;

        Gson gson = new Gson();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < astronautList.size(); i++) {
            ids.add(astronautList.get(i).getId());
        }
        return gson.toJson(ids);
    }

    @TypeConverter
    public static List<Astronaut> fromJsonToAstronautList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> idList = gson.fromJson(json, type);
        List<Astronaut> astronautList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            Astronaut astronaut = new Astronaut(idList.get(i));
            astronautList.add(astronaut);
        }
        return astronautList;
    }

    // SpaceStation
    @TypeConverter
    public static String fromSpaceStationListToJson(List<SpaceStation> spaceStationList) {

        if (spaceStationList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<SpaceStation>>() {}.getType();
        List<SpaceStation> minSpaceStationList = new ArrayList<>();
        for (SpaceStation spaceStation : spaceStationList) {
            minSpaceStationList.add(SpaceStation.buildMinSpaceStation(spaceStation));
        }
        return gson.toJson(minSpaceStationList, type);
    }

    @TypeConverter
    public static List<SpaceStation> fromJsonToSpaceStationList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<SpaceStation>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static Integer fromSpaceStationToInteger(SpaceStation spaceStation) {

        if (spaceStation == null) return null;

        return spaceStation.getId();
    }

    @TypeConverter
    public static SpaceStation fromIntegerToSpaceStation(Integer id) {

        if (id == null) return null;

        return new SpaceStation(id);
    }

    // DockingLocation
    @TypeConverter
    public static String fromDockingLocationListToJson(List<DockingLocation> dockingLocationList) {

        if (dockingLocationList == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<DockingLocation>>() {}.getType();
        return gson.toJson(dockingLocationList, type);
    }

    @TypeConverter
    public static List<DockingLocation> fromJsonToDockingLocationList(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<DockingLocation>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String fromDockingLocationToJson(DockingLocation dockingLocation) {

        if (dockingLocation == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<DockingLocation>() {}.getType();
        return gson.toJson(dockingLocation, type);
    }

    @TypeConverter
    public static DockingLocation fromJsonToDockingLocation(String json) {

        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<DockingLocation>() {}.getType();
        return gson.fromJson(json, type);
    }
}