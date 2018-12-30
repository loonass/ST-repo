/**
 *  Copyright 2016 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
	definition(name: "LED 3 Colour Indicator", namespace: "loonass", author: "Mike Harvey"){
		capability "Actuator"
		capability "Configuration"
		capability "Health Check"
        capability "Indicator"
        capability "Polling"
		capability "Refresh"
		capability "Sensor"
        capability "Switch"
		capability "Switch Level"

		attribute "colour","string"

		command "Green"
		command "Orange"
		command "Red"


		//fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0000,0019"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019"
		//fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0000,0019", manufacturer: "CREE", model: "Connected A-19 60W Equivalent", deviceJoinName: "Cree Connected Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000, 0B04, FC0F", outClusters: "0019", manufacturer: "OSRAM", model: "Classic A60 W clear", deviceJoinName: "OSRAM LIGHTIFY LED Smart Connected Light"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000, 0B04, FC0F", outClusters: "0019", manufacturer: "OSRAM", model: "Classic A60 W clear - LIGHTIFY", deviceJoinName: "OSRAM LIGHTIFY LED Smart Connected Light"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000, FC0F", outClusters: "0019", manufacturer: "OSRAM", model: "CLA60 OFD OSRAM", deviceJoinName: "OSRAM LIGHTIFY LED Classic A60 Dimming"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019", manufacturer: "Philips", model: "LWB004", deviceJoinName: "Philips Hue White"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019", manufacturer: "Philips", model: "LWB006", deviceJoinName: "Philips Hue White"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019", manufacturer: "Philips", model: "LWB007", deviceJoinName: "Philips Hue White"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019", manufacturer: "Philips", model: "LWB010", deviceJoinName: "Philips Hue White"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019", manufacturer: "Philips", model: "LWB014", deviceJoinName: "Philips Hue White"
		fingerprint inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI bulb E26 opal 1000lm", deviceJoinName: "IKEA TRADFRI LED Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI bulb E12 W op/ch 400lm", deviceJoinName: "IKEA TRADFRI LED Bulb"
		//IKEA bulb GU10 monitors model as TRADFRI bulb "E17" or "GU10"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI bulb E17 W op/ch 400lm", deviceJoinName: "IKEA TRADFRI LED Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI bulb GU10 W 400lm", deviceJoinName: "IKEA TRADFRI LED Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI bulb E27 W opal 1000lm", deviceJoinName: "IKEA TRADFRI LED Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RS 125", deviceJoinName: "innr Smart Spot"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RB 165", deviceJoinName: "innr Smart Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RB 175 W", deviceJoinName: "innr Smart Bulb (Warm Dimming)"
		fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0702, 0B05", outClusters: "0019", manufacturer: "sengled", model: "E14-U43", deviceJoinName: "Sengled E14-U43"

	}

	// simulator metadata
	simulator {
		// status messages
		status "on": "on/off: 1"
		status "off": "on/off: 0"

		// reply messages
		reply "zcl on-off on": "on/off: 1"
		reply "zcl on-off off": "on/off: 0"
	}

	// UI tile definitions
	tiles(scale: 2) {
			standardTile("colourTile", "device.colour", width: 6, height: 6) {
					state "Green", label:'${currentValue}', action: "Orange", backgroundColor: "#00FF00"
					state "Orange", label:'${currentValue}', action: "Red", backgroundColor: "#FFA500"
					state "Red", label:'${currentValue}', action: "Green", backgroundColor: "#FF0000"
			}

		main(["colourTile"])
		details(["colourTile"])
	}
}

preferences {
		input name: "shade", type: "number", title: "Orange Colour", description: "Set orange colour", required: true,
			displayDuringSetup: true
}

def parse(String description) {
	//log.debug "description is $description"

	def resultMap = zigbee.getEvent(description)
	if (resultMap) {
		sendEvent(resultMap)
	} else {
		log.debug "DID NOT PARSE MESSAGE for description : $description"
		log.debug zigbee.parseDescriptionAsMap(description)
	}
}

def off() {
	zigbee.off()
}

def on() {
	zigbee.on()
}

def setLevel(value) {
	zigbee.setLevel(value)
}

def refresh() {
	zigbee.onOffRefresh() + zigbee.levelRefresh()
}

def poll() {
	refresh()
    log.debug "Poll()"
}

/**
 * PING is used by Device-Watch in attempt to reach the Device
 * */
def ping() {
	return zigbee.levelRefresh()
    log.debug "Ping()"
}

def healthPoll() {
	log.debug "healthPoll()"
	def cmds = refresh()
	cmds.each { sendHubCommand(new physicalgraph.device.HubAction(it)) }
}

def configureHealthCheck() {
	Integer hcIntervalMinutes = 30
	if (!state.hasConfiguredHealthCheck) {
		log.debug "Configuring Health Check, Reporting"
		unschedule("healthPoll", [forceForLocallyExecuting: true])
		runEvery5Minutes("healthPoll", [forceForLocallyExecuting: true])
		// Device-Watch allows 2 check-in misses from device
		sendEvent(name: "checkInterval", value: hcIntervalMinutes * 60, displayed: false, data: [protocol: "zigbee", hubHardwareId: device.hub.hardwareID])
		state.hasConfiguredHealthCheck = true
	}
}

def configure() {
	log.debug "configure()"
	configureHealthCheck()
	zigbee.onOffConfig() + zigbee.levelConfig() + zigbee.onOffRefresh() + zigbee.levelRefresh()
}

def updated() {
	log.debug "Updated ${settings}"
	configureHealthCheck()
}

def Green() {
	sendEvent(name: "colour", value: "Green")
	sendEvent(name: "level", value: 100)
	sendEvent(name: "switch", value: "on")
	log.debug "green"
    zigbee.setLevel("100") + zigbee.on()
}

def Orange() {
	sendEvent(name: "colour", value: "Orange")
	sendEvent(name: "level", value: shade)
	sendEvent(name: "switch", value: "on")
	log.debug "orange"
	zigbee.setLevel(shade) + zigbee.on()

}

def Red() {
	sendEvent(name: "colour", value: "Red")
	sendEvent(name: "level", value: 0)
	sendEvent(name: "switch", value: "off")
    log.debug "red"
	zigbee.setLevel("0") + zigbee.off()
}