package com.erikriosetiawan.globofly.helpers

import com.erikriosetiawan.globofly.models.Destination

object SampleData {

    val DESTINATIONS = ArrayList<Destination>()

    private var COUNT = 5

    private var dummy_description =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce accumsan quis justo quis hendrerit. Curabitur a ante neque. Fusce nec mauris sodales, auctor sem at, luctus eros. Praesent aliquam nibh neque. Duis ut suscipit justo, id consectetur orci. Curabitur ultricies nunc eu enim dignissim, sed laoreet odio blandit."

    init {
        // Add some sample items
        val newDetinationOne = Destination()
        newDetinationOne.id = 1
        newDetinationOne.city = "New Delhi"
        newDetinationOne.description = dummy_description
        newDetinationOne.country = "India"
        DESTINATIONS.add(newDetinationOne)

        val destinationTwo = Destination()
        destinationTwo.id = 2
        destinationTwo.city = "Bangkok"
        destinationTwo.description = dummy_description
        destinationTwo.country = "Thailand"
        DESTINATIONS.add(destinationTwo)

        val destinationThree = Destination()
        destinationThree.id = 3
        destinationThree.city = "New York"
        destinationThree.description = dummy_description
        destinationThree.country = "USA"
        DESTINATIONS.add(destinationThree)

        val destinationFour = Destination()
        destinationFour.id = 4
        destinationFour.city = "London"
        destinationFour.description = dummy_description
        destinationFour.country = "United Kingdom"
        DESTINATIONS.add(destinationFour)

        val destinationFive = Destination()
        destinationFive.id = 5
        destinationFive.city = "Sydney"
        destinationFive.description = dummy_description
        destinationFive.country = "Australia"
        DESTINATIONS.add(destinationFive)
    }

    fun addDestinations(item: Destination) {
        item.id = COUNT
        DESTINATIONS.add(item)
        COUNT += 1
    }

    fun getDestinationsById(id: Int): Destination? {
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == id) {
                return DESTINATIONS[i]
            }
        }

        return null
    }

    fun deleteDestination(id: Int) {
        var destinationToRemove: Destination? = null

        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == id) {
                destinationToRemove = DESTINATIONS[i]
            }
        }

        if (destinationToRemove != null) {
            DESTINATIONS.remove(destinationToRemove)
        }
    }

    fun updateDestination(destination: Destination) {
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == destination.id) {
                val destinationToUpdate = DESTINATIONS[i]

                destinationToUpdate.city = destination.city
                destinationToUpdate.description = destination.description
                destinationToUpdate.country = destination.country
            }
        }
    }
}