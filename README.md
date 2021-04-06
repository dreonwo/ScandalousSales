# **Scandalous Sales** by Monica Howze, Aaron Holloman & Dreon Wheatley-Owens

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A comparison app used to display products and their differing prices from various grocery locations.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Shopping**
- **Mobile: More convenient than a website because a user can quickly open this app on a phone, scan a product SKU with their camera and compare prices no matter where they are.**
- **Story: Because prices of products can wildly vary, users will appreciate the ability to get the best possible price for a product. It is also convenient because instead of having to browse multiple websites in a browser, users can just browse the app and see all results in one place.**
- **Market: There is a large market for this app because it is for all people that shop frequently, especially teenagers and young adults. Our friends and family would like this app because they want to get the best prices quickly and easily.**
- **Habit: If they are a frequent shopper, they might become addicted to the app, using it every day or several times per week to compare item prices. If they shop once a while, they will use the app a few times per month or so. The average user will consume the app's content.**
- **Scope: Most of the basic features are what we did for previous apps, so it might only take a few weeks. A simple version of the app would still be helpful for the user. The app is simple enough for the user to understand its purpose without a tutorial being required.**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can view products online
* Allow user to scan barcodes and get results for multiple stores.

**Optional Nice-to-have Stories**

* User can sort items by category
* Create History of scanned items
    * Swipe to refresh item list
* Give navigation to nearby locations for a specific store
* Search bar for finding items by text search

### 2. Screen Archetypes
* Camera
    * User can scan product barcodes and tap image of product to be redirected to a detailed screen with the prices of different grocery stores.
* Stream(Results Feed)
   * User can view products online.
* Stream(History Feed)
   * User can view products online. User can view items that they have already scanned previously on the history screen.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Camera
* Search Results
* History

**Flow Navigation** (Screen to Screen)

* Camera
    * Product Screen -> Results Feed
* History

## Wireframes
[Add picture of your hand sketched wireframes in this section]

### [BONUS] Digital Wireframes & Mockups
<img src="Scandalous Sales.png" width=600>
### [BONUS] Interactive Prototype

# **Scandalous Sales** by Monica Howze, Aaron Holloman & Dreon Wheatley-Owens

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A comparison app used to display products and their differing prices from various electronic goods locations.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Shopping**
- **Mobile: More convenient than a website because a user can quickly open this app on a phone, scan a product SKU with their camera and compare prices no matter where they are.**
- **Story: Because prices of products can wildly vary, users will appreciate the ability to get the best possible price for a product. It is also convenient because instead of having to browse multiple websites in a browser, users can just browse the app and see all results in one place.**
- **Market: There is a large market for this app because it is for all people that shop frequently, especially teenagers and young adults. Our friends and family would like this app because they want to get the best prices quickly and easily.**
- **Habit: If they are a frequent shopper, they might become addicted to the app, using it every day or several times per week to compare item prices. If they shop once a while, they will use the app a few times per month or so. The average user will consume the app's content.**
- **Scope: Most of the basic features are what we did for previous apps, so it might only take a few weeks. A simple version of the app would still be helpful for the user. The app is simple enough for the user to understand its purpose without a tutorial being required.**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can view products online
* Allow user to scan barcodes and get results for multiple stores.
* User can sort items by category
* Create History of scanned items
    * Swipe to refresh item list
* Give navigation to nearby locations for a specific store
* Search bar for finding items by text search

**Optional Nice-to-have Stories**



### 2. Screen Archetypes
* Camera
    * User can scan product barcodes and tap image of product to be redirected to a detailed screen with the prices of different grocery stores.
* Stream(Results Feed)
   * User can view products online.
* Stream(History Feed)
   * User can view products online. User can view items that they have already scanned previously on the history screen.
* Stream(Main Screen)
    * User can search for products via the search bar or they can click the scan item button to be led to the camera for scanning.
* Detail Screen(Compare Screen)
    * User can view the various pricing for the item in different retail locations.

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

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | itemId      | String   | unique id for each product |
   | searchbar     | Pointer to database| search for products |
   | image         | File     | image for product |
   | itemPrice     | Int      | price of item |
   
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

