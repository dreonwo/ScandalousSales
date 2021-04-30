# **Scandalous Sales** by Monica Howze, Aaron Holloman & Dreon Wheatley-Owens

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A price comparison app sourced by its community used to display electronic products and their differing prices from various goods locations.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Social Media**
- **Mobile: More convenient than a website because a user can quickly open this app on a phone, scan a product SKU with their camera and compare prices no matter where they are.**
- **Story: Because prices of products can wildly vary, users will appreciate the ability to get the best possible price for a product. Users can just browse the app and send posts to specify the product details, which include the image, the price, and the rating.**
- **Market: There is a large market for this app because it is for all people that shop frequently, especially teenagers and young adults. Our friends and family would like this app because they want to get the best prices quickly and easily.**
- **Habit: If they are a frequent shopper, they might become addicted to the app, using it every day or several times per week to compare item prices. If they shop once a while, they will use the app a few times per month or so. The average user will consume the app's content.**
- **Scope: Most of the basic features are what we did for previous apps, so it might only take a few weeks. A simple version of the app would still be helpful for the user. The app is simple enough for the user to understand its purpose without a tutorial being required.**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* [x] User can register
* [x] User can login
* [x] User can post items
    [x] User can add the upc (by scanning with camera or typing it), price, image (optional), & description (optional) of items
* [x] Search bar for finding items by name or upc
* [x] User can view items 
    * [x] User can see location of items, their upc & their image and price if available
* [] User can access history of items they scanned through their profile

**Optional Nice-to-have Stories**
* User can sort search results by lowest price, highest price or newest scans.
* User can edit their post after creating it.
* [x] Have search bar on main page 
* Users can rate other users


### 2. Screen Archetypes

* Register/Login
    * User can register to make an account or login to an existing one.
* Profile
    * User can view their own profile and others' profiles. They will include the products the user scanned.
* Post
    * User can post product info and add an image of product to be sent to a database  with the product name, upc, and image.
* Stream(Search Feed)
   * User can search for products via the search bar or they can click the scan item button to be led to the camera for scanning.
* Stream(History Feed)
   * User can view items that they have already scanned previously on the history screen.
* Stream(Main Screen)
    * Show popular items
* Detail Screen(Item Details)
    * User can view the image, upc, and pricing for a particular product they click on.


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Camera
* Main Screen
* History

**Flow Navigation** (Screen to Screen)

* Camera
    * Product Screen -> Results Feed
* History
* Main Screen
    * Camera

## Wireframes
[Add picture of your hand sketched wireframes in this section]

### [BONUS] Digital Wireframes & Mockups
<img src="main_screen.png" width=600>
<img src="item_screen.png" width=600>

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models

#### Search Results
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | itemName       | String   | name of product |
   | image         | File     | image for product |
   | itemPrice     | Int      | price of product |
   
#### Posts
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | itemName      | String   | name of product |
   | image         | File     | image for product |
   | itemPrice     | Int      | price of product |
   
#### User
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | UserId        | String   | Word(s) used to pull search|
   | FirstName     | String   | User's first name |
   | LastName      | String   | User's last name |
   | Username      | String   | 
   | Password      | String   |
   | userRating    | Int      | Number signifying the authenticity of a user (1-5)

#### History
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | itemName       | String   | name of product |
   | image         | File     | image for product |
   | itemPrice     | Int      | price of product |
   | userId        | Int      | name of user who uploaded product|


### Networking
| 

#### List of network requests by screen

   - Home Feed Screen
      - (Read/GET) Fetch list of products
      - (Read/GET) Query product prices
      - (Read/GET) Query product descriptions
      - (READ/GET) List of stores products are being fetched from
   - History Screen
      - Query previously fetched results
      - Query Time of fetched results
      - 
#### [OPTIONAL:] Existing API Endpoints
##### Google Maps API [https://developers.google.com/maps/gmp-get-started](https://developers.google.com/maps/gmp-get-started)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /products/list   | list products
    `GET`    | /products/get-details | get product details 
    `GET`    | /products/get-description | return description of products
