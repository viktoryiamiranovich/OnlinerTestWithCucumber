Feature: Filter Onliner Catalog products
  This feature is for verifying that search results
  on the Catalog Page match the specified filters

  Scenario:
    Given User is on the Onliner Home Page
    When User clicks 'Каталог' on Onliner Home Page
    And User selects 'Электроника' on Catalog Page
    And moves to 'Телевидение и видео' and open 'Телевизоры' section
    And User selects filters on TV page
      | BRAND       | Samsung             |
      | MAXPRICE    | 1500                |
      | RESOLUTION  | 1920x1080 (Full HD) |
      | MINDIAGONAL | 40"                 |
      | MAXDIAGONAL | 50"                 |
    Then correct search results are displayed
