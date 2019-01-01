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
	definition(name: "Dog food indicator", namespace: "loonass", author: "Mike Harvey", ocfDeviceType: "oic.d.light", runLocally: false, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true) {
		capability "Actuator"
		capability "Configuration"
		capability "Health Check"
        capability "Indicator"
        capability "Polling"
		capability "Refresh"
		capability "Sensor"
        capability "Switch"
		capability "Switch Level"

		attribute "counter", "number"
		attribute "occurrences", "number"
		attribute "colour","string"

		command "counterUp"
		command "counterDown"
		command "occurrencesReset"
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
			multiAttributeTile(name:"valueTile", type:"generic") {
				tileAttribute("device.counter", key: "PRIMARY_CONTROL") {
					attributeState "5", label: '${currentValue}', icon: "st.Office.office6", action: "off", backgroundColor:"#bc2323" //Red
					attributeState "4", label: '${currentValue}', icon: "st.Office.office6", action: "off", backgroundColor:"#e86d13" //Orange
					attributeState "3", label: '${currentValue}', icon: "st.Office.office6", action: "off", backgroundColor:"#e86d13" //Orange
					attributeState "2", label: '${currentValue}', icon: "st.Office.office6", action: "off", backgroundColor:"#e86d13" //Orange
					attributeState "1", label: '${currentValue}', icon: "st.Office.office6", action: "off", backgroundColor:"#e86d13" //Orange
					attributeState "0", label: '${currentValue}', icon: "st.Food & Dining.dining18", action: "off", backgroundColor:"#44b621" //Green
					}
				tileAttribute("device.switch", key: "SECONDARY_CONTROL") {
					attributeState "on", label:"On", action:"switch.off", icon:"st.illuminance.illuminance.bright", backgroundColor:"#00A0DC", nextState:"turningOff"
					attributeState "off", label:"Off", action:"switch.on", icon:"st.illuminance.illuminance.dark", backgroundColor:"#ffffff", nextState:"turningOn"
					attributeState "turningOn", label:"Turning On", action:"switch.off", icon:"st.illuminance.illuminance.light", backgroundColor:"#00A0DC", nextState:"turningOff"
					attributeState "turningOff", label:"Turning Off", action:"switch.on", icon:"st.illuminance.illuminance.light", backgroundColor:"#ffffff", nextState:"turningOn"
					}
				tileAttribute("device.counter", key: "VALUE_CONTROL") {
					attributeState "VALUE_UP", action: "counterUp"
					attributeState "VALUE_DOWN", action: "counterDown"
					}
				tileAttribute ("device.level", key: "SLIDER_CONTROL") {
					attributeState "level", action:"setLevel"
                    }
			}
			valueTile("occurrence", "device.occurrences", width: 2, height: 2) {
					state "number", label:'${currentValue}', defaultState: true, action: "occurrencesReset", backgroundColors:[
					[value: 0, color: "#ffffff"],	//White
					[value: 1, color: "#00a0dc"],	//Blue
					[value: 2, color: "#e86d13"]	//Orange
					]
			}

		main(["valueTile"])
		details(["valueTile","occurrence"])
	}
}

preferences {
		input name: "hours", type: "number", title: "Hours", description: "How many hours before each feed?", required: true,
			displayDuringSetup: true
		input name: "shade", type: "number", title: "Orange Colour", description: "Set orange colour.", required: true,
			displayDuringSetup: true
		input name: "orangetime", type: "number", title: "Orange Time", description: "Set time orange is lit for.", required: true,
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
	zigbee.off() + ["delay 1500"] + zigbee.onOffRefresh() + sendEvent(name:"counter", value: hours) + occurrenceUp()
}

def on() {
	zigbee.on() + ["delay 1500"] + zigbee.onOffRefresh() + sendEvent(name:"counter", value: 0)
}

def setLevel(value) {
	zigbee.setLevel(value) + zigbee.onOffRefresh() + zigbee.levelRefresh()
	//adding refresh because of ZLL bulb not conforming to send-me-a-report
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

def occurrenceUp() {
	def occurrences = device.latestValue("occurrences") as Integer ?: 0
	occurrences = occurrences + 1
	setOccurrences(occurrences)
}

def setCounter(number) {
	sendEvent(name: "counter", value: number)
}

def setOccurrences(number) {
	sendEvent(name: "occurrences", value: number)
}

def occurrencesReset() {
	sendEvent(name: "occurrences", value: 0)
}

def counterUp() {
	def counter = device.latestValue("counter") as Integer ?: 0
	if (counter < hours) {
		counter = counter + 1
		setCounter(counter)
	}
	if (counter > orangetime) {
	Red()
    } else {
	if (counter <= orangetime && counter >0) {
	Orange()
	} else {
	if (counter == 0) {
	Green()
	}
    }
    }
}

def counterDown() {
	def counter = device.latestValue("counter") as Integer ?: 0
	if (counter > 0) {
		counter = counter - 1
		setCounter(counter)
	}
	if (counter > orangetime) {
	Red()
    } else {
	if (counter <= orangetime && counter >0) {
	Orange()
	} else {
	if (counter == 0) {
	Green()
	}
    }
    }
}

def Green() {
	sendEvent(name: "colour", value: "Green")
	sendEvent(name: "level", value: 100)
	sendEvent(name: "switch", value: "on")
	log.debug "green"
    zigbee.setLevel(100) + zigbee.on()
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
	zigbee.setLevel(0) + zigbee.off()
}