package de.abg.jreichert.activeanno.jpa.example

import de.abg.jreichert.activeanno.jpa.example.internal.Location
import de.abg.jreichert.activeanno.jpa.example.internal.LocationManager
import de.abg.jreichert.activeanno.jpa.example.internal.Unit
import de.abg.jreichert.activeanno.jpa.example.internal.Version

class Main {
	
	def static void main(String[] args) {
		val location = locationTestdata
		val locationManager = new LocationManager
		val savedLocation = locationManager.saveLocation(location)
		val urls = locationManager.getLocationURLsContainingUnitWithVersion("cde.feature", "0.0.1.201304242052")
		urls.forEach[System.err.println(it)]
		locationManager.deleteLocation(savedLocation)
	}
	
	def static private locationTestdata() {
		return new Location() => [
			timestamp = "1367912476285"
			url =  "file://testdata/updatesite/complex"
			parentLocation = null
			aggregatedLocations = newTreeSet(
				[a,b|a.url.compareTo(b.url)],
				new Location(it) => [
					timestamp = "1367932476293"
					url =  "file://testdata/updatesite/complex/cdefgh"
					units = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Unit(it) => [
							name = "cde.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									name = "0.0.1.201304242052"
								],
								new Version(it) => [
									name = "0.0.2.201305242052"
								]	
							)
						],
						new Unit(it) => [
							name = "fgh.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									name = "0.0.3.201306242052"
								],
								new Version(it) => [
									name = "0.0.4.201307242052"
								]	
							)
						]
					)
				],
				new Location(it) => [
					timestamp = "1367922476291"
					url =  "file://testdata/updatesite/complex/ijklmn"
					units = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Unit(it) => [
							name = "ijk.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									name = "0.0.5.201304242052"
								],
								new Version(it) => [
									name = "0.0.6.201305242052"
								]	
							)
						],
						new Unit(it) => [
							name = "lmn.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									name = "0.0.7.201306242052"
								],
								new Version(it) => [
									name = "0.0.8.201307242052"
								]	
							)
						]
					)
				]
			)
		]		
	}
}