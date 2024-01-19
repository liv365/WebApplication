;(function ($) {

    // Multiple instantiation (divs 1 and 2)

    $('#my_calendar_en').rescalendar({
        id: 'my_calendar_en',
        format: 'YYYY-MM-DD',
        refDate: '2019-03-08',
        jumpSize: 30,
        disabledDays: ['2019-01-01', '2019-01-07', '2019-04-18', '2019-04-19', '2019-05-01', '2019-05-02', '2019-05-13', '2019-08-15', '2019-10-12', '2019-11-01', '2019-12-06', '2019-12-09', '2019-12-20', '2019-12-24', '2019-12-25', '2019-12-31'],
        disabledWeekDays: [5, 6],
        data: [
            {
                id: 1,
                name: 'Double Room with Sea View',
                startDate: '2019-03-01',
                endDate: '2019-03-02',
                customClass: 'greenClass',
                title: '2',
                data: 2
            },
            {
                id: 2,
                name: 'Triple Room with Sea View',
                startDate: '2019-03-05',
                endDate: '2019-03-15',
                customClass: 'blueClass',
                title: 'Title 2 en'
            },
            {
                id: 3,
                name: 'Double or Twin Room with Balcony',
                startDate: '2019-03-05',
                endDate: '2019-03-08',
                customClass: 'greenClass'
            }
        ],

        dataKeyField: 'name',
        dataKeyValues: ['Double Room with Sea View', 'Triple Room with Sea View', 'Quadruple Room with Sea View', 'Double or Twin Room with Balcony']

    });

    $('#my_calendar_simple').rescalendar({
        id: 'my_calendar_simple',
        dataKeyField: 'name',
        dataKeyValues: ['Double Room with Sea View']
    });

    $('#my_calendar_calSize').rescalendar({
        id: 'my_calendar_calSize',
        jumpSize: 30,
        calSize: 10,
        data: [
            {
                id: 1,
                name: 'Double Room with Sea View',
                startDate: '2019-03-01',
                endDate: '2019-03-03',
                customClass: 'greenClass'
            },
            {
                id: 2,
                name: 'Triple Room with Sea View',
                startDate: '2019-03-05',
                endDate: '2019-03-15',
                customClass: 'blueClass',
                title: 'Title 2 en'
            },
            {
                id: 3,
                name: 'Double or Twin Room with Balcony',
                startDate: '2019-03-05',
                endDate: '2019-03-08',
                customClass: 'greenClass'
            }
        ],

        dataKeyField: 'name',
        dataKeyValues: ['Double Room with Sea View', 'Triple Room with Sea View', 'Quadruple Room with Sea View', 'Double or Twin Room with Balcony']
    });


}(jQuery));
