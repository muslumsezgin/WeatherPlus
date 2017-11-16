package com.muslum.Weather.uı;


import com.muslum.Weather.backend.controller.WeatherController;
import com.muslum.Weather.backend.model.City;
import com.muslum.Weather.backend.model.ResponseData;
import com.muslum.Weather.backend.model.WeathersModel;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Title("Weather+")
@Theme("mytheme")
@SpringUI
@UIScope
public class WeatherUI extends UI {

    private static final Logger logger = Logger.getLogger(WeatherUI.class);
    private final WeatherController weatherController;
    private ResponseData weatherDatas;
    private List<City> saveCities;
    private boolean showCitiesPage;

    @Autowired
    public WeatherUI(WeatherController weatherController) {
        this.weatherController = weatherController;
        this.showCitiesPage =  false;
        this.saveCities = new ArrayList<>();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final AbsoluteLayout mainlayout = new AbsoluteLayout();
        mainlayout.setStyleName("my-layout");
        setContent(mainlayout);

        AbsoluteLayout layout = new AbsoluteLayout();
        layout.setStyleName("my-background");
        mainlayout.addComponent(layout);


        HorizontalLayout connector = new HorizontalLayout();
        connector.setWidth(90, Unit.PERCENTAGE);
        connector.setHeight(93, Unit.PERCENTAGE);
        connector.setSpacing(false);


        VerticalLayout searchLayout = new VerticalLayout();
        searchLayout.setWidth("100%");
        searchLayout.setHeight("100%");
        searchLayout.setStyleName("searchLayout");

        TextField cityInput = new TextField();
        cityInput.setWidth(90, Unit.PERCENTAGE);
        cityInput.addStyleName("cityInput");
        cityInput.setPlaceholder("İstanbul");


        cityInput.addShortcutListener(new ShortcutListener("Search", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object o, Object o1) {
                if (!cityInput.getValue().isEmpty()) {
                    weatherDatas = weatherController.getServices(cityInput.getValue());
                    if (weatherDatas == null) {
                        Notification.show("City is not found", Notification.Type.HUMANIZED_MESSAGE);
                        showCitiesPage = true;
                    }
                    showCitiesPage=false;
                    init(vaadinRequest);
                }
            }
        });

        searchLayout.addComponent(cityInput);
        searchLayout.setComponentAlignment(cityInput, Alignment.TOP_LEFT);

        GridLayout weatherData;
        if (showCitiesPage)
            weatherData = new GridLayout(2,saveCities.size()+1);
        else
            weatherData = new GridLayout(2, 3);


        weatherData.setWidth("100%");
        weatherData.setHeight("100%");
        weatherData.addStyleName("weatherData");

        if (weatherDatas == null){
            Image image = getImage("emoji" + ".png");
            searchLayout.addComponent(image);
            searchLayout.setComponentAlignment(image,Alignment.MIDDLE_CENTER);
            citiesLayout(weatherData,vaadinRequest);
        }else{
            if (showCitiesPage){
                citiesLayout(weatherData,vaadinRequest);
            }else
                weekDayWeatherInfoLayout(weatherData, weatherDatas.getList());

            searchLayout = dayDetailLayout(weatherDatas.getList().get(0), weatherDatas.getCity(), searchLayout,vaadinRequest);
        }

        connector.addComponent(searchLayout);
        connector.addComponent(weatherData);
        mainlayout.addComponent(connector, "left: 180px; top: 60px;");

    }

    /**
     *
     * @param weatherData verilerin icine eklenecegi layout
     * @param request vaddin request
     */
    private void citiesLayout(GridLayout weatherData,VaadinRequest request){
        saveCities.forEach(city -> {
            ResponseData responseData = weatherController.getServices(city.getName());

            VerticalLayout infoLayout = new VerticalLayout();
            Button infoDay = new Button(responseData.getCity().getName()+","+responseData.getCity().getCountry());
            HorizontalLayout degreeLayout = new HorizontalLayout();
            Image weatherImage = getImage(responseData.getList().get(0).getWeather().get(0).getMain() +".svg");
            Label degreeDay= new Label(responseData.getList().get(0).getTemp().getDay() + "\u00b0");
            Label weatherDesc = new Label(responseData.getList().get(0).getWeather().get(0).getDescription().toUpperCase(Locale.ENGLISH));
            Label minMaxDegree = new Label("Min: " + String.format("%.1f",responseData.getList().get(0).getTemp().getMin())
                    + " Max: " + String.format("%.1f",responseData.getList().get(0).getTemp().getMax())) ;

            weatherImage.setHeight(80, Unit.PERCENTAGE);
            weatherImage.setWidth(80, Unit.PERCENTAGE);infoDay.setStyleName("dayName");
            degreeDay.setStyleName("weatherDesc");
            weatherDesc.setStyleName("weatherPlusText");
            minMaxDegree.setStyleName("weatherPlusText");

            VerticalLayout weatherInfo = new VerticalLayout();
            weatherInfo.setSpacing(false);
            weatherInfo.addComponent(degreeDay);
            weatherInfo.addComponent(weatherDesc);
            weatherInfo.addComponent(minMaxDegree);

            degreeLayout.addComponent(weatherImage);
            degreeLayout.addComponent(weatherInfo);

            infoLayout.addComponent(infoDay);
            infoLayout.addComponent(degreeLayout);

            infoDay.addClickListener(clickEvent -> {
                weatherDatas = responseData;
                showCitiesPage = false;
                init(request);
            });

            weatherData.addComponent(infoLayout);
        });

    }

    /**
     *
     * @param weatherData verilerin eklencegi layout
     * @param weathersModels hava durumu bilgilerini iceren liste
     */
    private void weekDayWeatherInfoLayout(GridLayout weatherData, List<WeathersModel> weathersModels) {

        for (int dayData = 1; dayData < 7; dayData++) {
            LocalDateTime date = new Timestamp(weathersModels.get(dayData).getDt() * 1000L).toLocalDateTime();

            VerticalLayout infoLayout = new VerticalLayout();
            Label infoDay = new Label(DateTimeFormatter.ofPattern("EEEE").format(date));
            HorizontalLayout degreeLayout = new HorizontalLayout();
            Image weatherImage = getImage(weathersModels.get(dayData).getWeather().get(0).getMain() + ".svg");
            Label degreeDay= new Label(weathersModels.get(dayData).getTemp().getDay() + "\u00b0");
            Label weatherDesc = new Label(weathersModels.get(dayData).getWeather().get(0).getDescription().toUpperCase(Locale.ENGLISH));
            Label minMaxDegree = new Label("Min: " + String.format("%.1f",weathersModels.get(dayData).getTemp().getMin()) + " Max: "
                    + String.format("%.1f",weathersModels.get(dayData).getTemp().getMax())) ;

            weatherImage.setHeight(80, Unit.PERCENTAGE);
            weatherImage.setWidth(80, Unit.PERCENTAGE);
            infoDay.setStyleName("dayName");
            degreeDay.setStyleName("weatherDesc");
            weatherDesc.setStyleName("weatherPlusText");
            minMaxDegree.setStyleName("weatherPlusText");


            VerticalLayout weatherInfo = new VerticalLayout();
            weatherInfo.setSpacing(false);
            weatherInfo.addComponent(degreeDay);
            weatherInfo.addComponent(weatherDesc);
            weatherInfo.addComponent(minMaxDegree);

            degreeLayout.addComponent(weatherImage);
            degreeLayout.addComponent(weatherInfo);

            infoLayout.addComponent(infoDay);
            infoLayout.addComponent(degreeLayout);

            weatherData.addComponent(infoLayout);

        }

    }

    /**
     *
     * @param key image ismi
     * @return ımage verisi
     */
    private Image getImage(String key) {
        Image image = new Image();
        image.setSource(new ThemeResource("images/" + key ));
        return image;

    }


    /**
     *
     * @param weathersModel hava durumu bilgileri
     * @param city sehir bilgileri
     * @param searchLayout verilerin eklenecegi layout
     * @param request vaadin request
     * @return yeni layout
     */
    private VerticalLayout dayDetailLayout(WeathersModel weathersModel, City city, VerticalLayout searchLayout,VaadinRequest request) {


        Image weatherImage = getImage(weathersModel.getWeather().get(0).getMain() + ".svg");
        weatherImage.setWidth(270, Unit.PIXELS);
        weatherImage.setHeight(270, Unit.PIXELS);

        Label cityName = new Label(city.getName() + "," + city.getCountry());
        Label degree = new Label(String.format("%.1f", weathersModel.getTemp().getDay()) + "\u00b0");
        LocalDateTime firstDate = new Timestamp(weathersModel.getDt() * 1000L).toLocalDateTime();
        Label dayName = new Label(DateTimeFormatter.ofPattern("EEEE d").format(firstDate));
        Label humidity = new Label(weathersModel.getSpeed() + " mph / %" + weathersModel.getHumidity());
        Label minMaxDegree = new Label("Min: " + weathersModel.getTemp().getMin() + " Max: " + weathersModel.getTemp().getMax());
        Label weatherDesc = new Label(weathersModel.getWeather().get(0).getDescription().toUpperCase(Locale.ENGLISH));

        cityName.addStyleName("cityNameInDay");
        degree.addStyleName("degreeInDay");
        dayName.setStyleName("weatherPlusText");
        humidity.setStyleName("weatherPlusText");
        minMaxDegree.setStyleName("weatherPlusText");
        weatherDesc.setStyleName("weatherDesc");

        VerticalLayout infoPlus = new VerticalLayout();
        infoPlus.addComponent(weatherDesc);
        infoPlus.addComponent(dayName);
        infoPlus.addComponent(humidity);
        infoPlus.addComponent(minMaxDegree);

        Button showButton = new Button(showCitiesPage ?  "Close Save Cities" : "Show Save Cities");
        Button saveButton = new Button("Save");

        saveButton.addClickListener(clickEvent -> {
            addCity();
            init(request);
        });
        showButton.addClickListener(clickEvent ->{
            showCitiesPage = !showCitiesPage;
            init(request);
        } );

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(showButton);
        horizontalLayout.addComponent(saveButton);


        searchLayout.addComponent(weatherImage);
        searchLayout.addComponent(degree);
        searchLayout.addComponent(infoPlus);
        searchLayout.addComponent(horizontalLayout);
        searchLayout.addComponent(cityName);

        searchLayout.setComponentAlignment(weatherImage, Alignment.TOP_LEFT);
        searchLayout.setComponentAlignment(degree, Alignment.TOP_RIGHT);
        searchLayout.setComponentAlignment(infoPlus, Alignment.BOTTOM_LEFT);
        searchLayout.setComponentAlignment(horizontalLayout, Alignment.BOTTOM_RIGHT);
        searchLayout.setComponentAlignment(cityName, Alignment.BOTTOM_RIGHT);

        return searchLayout;
    }

    /**
     * sehir kayıt etme
     */
    private void addCity() {
       if( saveCities.indexOf(weatherDatas.getCity() )== -1 ){
           saveCities.add(weatherDatas.getCity());
           logger.info("ADD CITY: " + weatherDatas.getCity().toString());
       }
    }

}