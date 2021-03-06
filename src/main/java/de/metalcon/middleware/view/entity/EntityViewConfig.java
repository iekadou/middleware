package de.metalcon.middleware.view.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.middleware.view.entity.impl.BandView;
import de.metalcon.middleware.view.entity.impl.CityView;
import de.metalcon.middleware.view.entity.impl.EventView;
import de.metalcon.middleware.view.entity.impl.GenreView;
import de.metalcon.middleware.view.entity.impl.InstrumentView;
import de.metalcon.middleware.view.entity.impl.RecordView;
import de.metalcon.middleware.view.entity.impl.TourView;
import de.metalcon.middleware.view.entity.impl.TrackView;
import de.metalcon.middleware.view.entity.impl.UserView;
import de.metalcon.middleware.view.entity.impl.VenueView;

/**
 * Together with EntityViewFactory, this file is used to provide access to
 * prototype access to EntityViews inside singleton beans. See:
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/
 * beans.html#beans-java-method-injection
 */
@Configuration
public class EntityViewConfig {

    @Bean
    @Scope("prototype")
    public BandView bandView() {
        return new BandView();
    }

    @Bean
    @Scope("prototype")
    public CityView cityView() {
        return new CityView();
    }

    @Bean
    @Scope("prototype")
    public EventView eventView() {
        return new EventView();
    }

    @Bean
    @Scope("prototype")
    public GenreView genreView() {
        return new GenreView();
    }

    @Bean
    @Scope("prototype")
    public InstrumentView instrumentView() {
        return new InstrumentView();
    }

    @Bean
    @Scope("prototype")
    public RecordView recordView() {
        return new RecordView();
    }

    @Bean
    @Scope("prototype")
    public TourView tourView() {
        return new TourView();
    }

    @Bean
    @Scope("prototype")
    public TrackView trackView() {
        return new TrackView();
    }

    @Bean
    @Scope("prototype")
    public UserView userView() {
        return new UserView();
    }

    @Bean
    @Scope("prototype")
    public VenueView venueView() {
        return new VenueView();
    }

    @Bean
    public EntityViewFactory entityViewFactory() {
        return new EntityViewFactory() {

            @Override
            public BandView createBandView() {
                return bandView();
            }

            @Override
            public CityView createCityView() {
                return cityView();
            }

            @Override
            public EventView createEventView() {
                return eventView();
            }

            @Override
            public GenreView createGenreView() {
                return genreView();
            }

            @Override
            public InstrumentView createInstrumentView() {
                return instrumentView();
            }

            @Override
            public RecordView createRecordView() {
                return recordView();
            }

            @Override
            public TourView createTourView() {
                return tourView();
            }

            @Override
            public TrackView createTrackView() {
                return trackView();
            }

            @Override
            public UserView createUserView() {
                return userView();
            }

            @Override
            public VenueView createVenueView() {
                return venueView();
            }

        };
    }

}
