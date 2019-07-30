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

import groovy.json.*
import java.text.SimpleDateFormat	//needed for formatDt

metadata {
	definition(name: "dog", namespace: "smartthings", author: "SmartThings", ocfDeviceType: "oic.d.light", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, genericHandler: "ZLL") {
		capability "Actuator"
		capability "Configuration"
		capability "Polling"
		capability "Refresh"
		capability "Switch"
		capability "Switch Level"
		capability "Health Check"

        attribute "LastFed", "string"
		attribute "timesFed", "number"
		attribute "colour","string"
        attribute "IsFed","string"
        attribute "PetName","string"

		command "On"
		command "Off"
		command "timesFedReset"
		command "Green"
		command "Orange"
		command "Red"
//        command "Pressed"
        command "timeStamp"


		//fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0000,0019"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0019"
		//fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000", outClusters: "0000,0019", manufacturer: "CREE", model: "Connected A-19 60W Equivalent", deviceJoinName: "Cree Connected Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000, 0B04, FC0F", outClusters: "0019", manufacturer: "OSRAM", model: "Classic A60 W clear", deviceJoinName: "OSRAM SMART+ LED Smart Connected Light"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000, 0B04, FC0F", outClusters: "0019", manufacturer: "OSRAM", model: "Classic A60 W clear - LIGHTIFY", deviceJoinName: "OSRAM SMART+ LED Smart Connected Light"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 1000, FC0F", outClusters: "0019", manufacturer: "OSRAM", model: "CLA60 OFD OSRAM", deviceJoinName: "OSRAM SMART+ LED Classic A60 Dimming"
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
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RS 125", deviceJoinName: "Innr Smart Spot White"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RB 165", deviceJoinName: "Innr Smart Bulb White"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RB 175 W", deviceJoinName: "Innr Smart Bulb Warm Dimming"
		fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0702, 0B05", outClusters: "0019", manufacturer: "sengled", model: "E14-U43", deviceJoinName: "Sengled E14-U43"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008", outClusters: "0019", manufacturer: "innr", model: "RB 145", deviceJoinName: "Innr Smart Candle White"
		//IKEA TRADFRI bulb E14 W op/ch 400lm: 01 C05E 0100 02 08 0000 0003 0004 0005 0006 0008 0B05 1000 04 0005 0019 0020 1000
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI bulb E14 W op/ch 400lm", deviceJoinName: "IKEA TRADFRI LED Bulb"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI transformer 10W", deviceJoinName: "TRÅDFRI Driver for wireless control 10W"
		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden", model: "TRADFRI transformer 30W", deviceJoinName: "TRADFRI Driver for wireless control 30W"
        	//Megaman INGENIUM ZB
        	fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, FFFF", outClusters: "0019",manufacturer: "Megaman", model: "ZLL-DimmableLight", deviceJoinName: "INGENIUM ZB Dimmable Light"
       		fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, FFFF", outClusters: "0019",manufacturer: "MEGAMAN", model: "BSZTM002", deviceJoinName: "INGENIUM ZB Dimmable A60 Bulb"
        	fingerprint profileId: "C05E", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, FFFF", outClusters: "0019",manufacturer: "MEGAMAN", model: "BSZTM003", deviceJoinName: "INGENIUM ZB Dimming Module"
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
			valueTile("text", "device.PetName", height: 2, width: 6, inactiveLabel: true, decoration: "flat") {
            state("text", label:"Does "+'${currentValue}'+" need to be fed?")
			}
			multiAttributeTile(name:"valueTile", type:"generic") {
				tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
					attributeState "off", label: "No", icon: "st.Office.office6", backgroundColor:"#bc2323" //Red
					attributeState "on", label: "Yes ", icon: "st.Food & Dining.dining18", action: "Off", backgroundColor:"#44b621" //Green
					}
				tileAttribute("device.switch", key: "SECONDARY_CONTROL") {
					attributeState "on", label:'${currentValue}', icon:"st.illuminance.illuminance.bright"
					attributeState "off", label:'${currentValue}', icon:"st.illuminance.illuminance.dark"
					}
				tileAttribute ("device.level", key: "SLIDER_CONTROL") {
					attributeState "level"//, action:"setLevel"
                    }
			}
			valueTile("timesFed", "device.timesFed", width: 2, height: 2) {
					state "number", label:"Elmo's been fed "+'${currentValue} times today', decoration: "flat", backgroundColors:[
					[value: 0, color: "#ffffff"],	//White
					[value: 1, color: "#00a0dc"],	//Blue
					[value: 2, color: "#e86d13"]	//Orange
					]
			}
			valueTile("LastPress", "device.LastFed", width: 4, height: 2) {
					state "date", label:'Elmo was last fed:\n${currentValue}'
			}

			valueTile("petsname", "device.PetName", width: 6, height: 1) {
					state "string", label:'${currentValue}'
			}

		main(["valueTile"])
		details(["text","valueTile","timesFed","LastPress","petsname"])
	}
}

		preferences {
			input name: "Name", type: "string", title: "Pet's Name", description: "Type your pets name?", required: false, defaultValue: "Pet", displayDuringSetup: true
//			input name: "shade", type: "number", title: "Orange Colour", description: "Set orange colour.", required: true, displayDuringSetup: true
}

// Parse incoming device messages to generate events
def parse(String description) {
	log.debug "description is $description"

	def resultMap = zigbee.getEvent(description)
	if (resultMap) {
		sendEvent(resultMap)
	} else {
		log.debug "DID NOT PARSE MESSAGE for description : $description"
		log.debug zigbee.parseDescriptionAsMap(description)
	}
}

def Off() {
    log.debug "off"
    Red()
}

def On() {
	log.debug "on"
    Green()
}

def setLevel(value, rate = null) {
	zigbee.setLevel(value) + zigbee.onOffRefresh() + zigbee.levelRefresh()
	//adding refresh because of ZLL bulb not conforming to send-me-a-report
}

def refresh() {
	zigbee.onOffRefresh() + zigbee.levelRefresh()
}

def poll() {
	refresh()
}

/**
 * PING is used by Device-Watch in attempt to reach the Device
 * */
def ping() {
	return zigbee.levelRefresh()
}

def healthPoll() {
	log.debug "healthPoll()"
	def cmds = refresh()
	cmds.each { sendHubCommand(new physicalgraph.device.HubAction(it)) }
}

def configureHealthCheck() {
	Integer hcIntervalMinutes = 12
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
	log.debug "updated()"
	configureHealthCheck()
    sendEvent(name: "PetName", value: Name)
}

def Green() {	//sets the colour to Green
	sendEvent(name: "colour", value: "Green")
	sendEvent(name: "level", value: 100)
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "IsFed", value: "No")
	log.debug "Green"
    zigbee.setLevel(100) + zigbee.on() + ["delay 1500"] + zigbee.onOffRefresh()
}

//def Orange() {	//sets the colour to Orange
//	sendEvent(name: "colour", value: "Orange")
//	sendEvent(name: "level", value: shade)
//	sendEvent(name: "switch", value: "on")
//	log.debug "Orange"
//	zigbee.setLevel(shade) + zigbee.on() + ["delay 1500"] + zigbee.onOffRefresh()
//}

def Red() {	//sets the colour to Red
	sendEvent(name: "colour", value: "Red")
	sendEvent(name: "level", value: 0)
	sendEvent(name: "switch", value: "off")
    sendEvent(name: "IsFed", value: "Yes")
    timesFedUp()
    timeStamp()
    log.debug "Red"
	zigbee.setLevel(0) + zigbee.off() + ["delay 1500"] + zigbee.onOffRefresh()
}

def timesFedUp() {	//adds 1 to timesFed
	def timesFed = device.latestValue("timesFed") as Integer ?: 0
	timesFed = timesFed + 1
	setTimesFed(timesFed)
}

def setTimesFed(number) {	//sets the occurances from occuranceUp
	sendEvent(name: "timesFed", value: number)
}

def timesFedReset() {	//for resetting from WebCore
	sendEvent(name: "timesFed", value: 0)
}

def timeStamp() {	//to insert time into LastFed attribute
	sendEvent(name: "LastFed", value: getDtNow())
}

def getDtNow() {	//gets current time for timeStamp
	def now = new Date()
	return formatDt(now, false)
}

def formatDt(dt, mdy = true) {	//formats as something useful
	def formatVal = mdy ? "MMM d, yyyy - h:mm:ss a" : "E dd MMM yyyy\nHH:mm:ss z"
	def tf = new SimpleDateFormat(formatVal)
	if(location?.timeZone) { tf.setTimeZone(location?.timeZone) }
	return tf.format(dt)
}