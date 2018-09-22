/**
 *  Copyright 2016 SmartThings, Inc.
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
	definition (name: "Countdown", namespace: "loonass", author: "Mike Harvey") {
		capability "Actuator"
		capability "Sensor"
		capability "Switch"
		capability "Switch Level"

		attribute   "counter", "number"
		attribute   "occurrences", "number"

		command "counterUp"
		command "counterDown"
		}

	tiles(scale: 2) {
			multiAttributeTile(name:"valueTile", type:"generic", canChangeIcon: true) {
				tileAttribute("device.counter", key: "PRIMARY_CONTROL") {
					attributeState "counter", label:'${currentValue}', icon: "st.Office.office6", backgroundColors:[
					[value: 0, color: "#44b621"],	//Green
					[value: 1, color: "#d04e00"],	//Orange
					[value: 2, color: "#bc2323"]	//Red
					]
					}
				tileAttribute("device.switch", key: "SECONDARY_CONTROL") {
					attributeState "on", label:"On", action:"switch.off", icon:"https://github.com/loonass/Smartthings/blob/master/devicetypes/loonass/countdown.src/On.png?raw=true", backgroundColor:"#00A0DC", nextState:"turningOff"
					attributeState "off", label:"Off", action:"switch.on", icon:"https://github.com/loonass/Smartthings/blob/master/devicetypes/loonass/countdown.src/Off.png?raw=true", backgroundColor:"#ffffff", nextState:"turningOn"
					attributeState "turningOn", label:"Turning On", action:"switch.off", icon:"https://github.com/loonass/Smartthings/blob/master/devicetypes/loonass/countdown.src/TOn.png?raw=true", backgroundColor:"#00A0DC", nextState:"turningOff"
					attributeState "turningOff", label:"Turning Off", action:"switch.on", icon:"https://github.com/loonass/Smartthings/blob/master/devicetypes/loonass/countdown.src/TOff.png?raw=true", backgroundColor:"#ffffff", nextState:"turningOn"
					}
				tileAttribute("device.counter", key: "VALUE_CONTROL") {
					attributeState "VALUE_UP", action: "counterUp"
					attributeState "VALUE_DOWN", action: "counterDown"
					}
			}
			valueTile("value1", "device.occurrences", width: 2, height: 2) {
					state "val", label:'${currentValue}', defaultState: true, backgroundColors:[
					[value: 0, color: "#ffffff"],	//White
					[value: 1, color: "#00a0dc"],	//Blue
					[value: 2, color: "#00a0dc"],	//Blue
					[value: 3, color: "#e86d13"]	//Orange
					]
			}

		main(["valueTile"])
		details(["valueTile","value1"])
	}
}

preferences {
		input name: "name", type: "string", title: "Dog's name", description: "What is your dog's name?", required: true,
			displayDuringSetup: true
		input name: "hours", type: "number", title: "Hours", description: "How many hours before each feed?", required: true,
			displayDuringSetup: true
}

def installed() {
}

def updated() {
}

def parse(String description) {
	// This is a simulated device. No incoming data to parse.
}

def on() {
	log.debug "on()"
	sendEvent(name: "switch", value: "on")
	sendEvent(name:"counter", value: 0)
}

def off() {
	log.debug "off()"
	sendEvent(name: "switch", value: "off")
	sendEvent(name:"counter", value: hours)
	runEvery1Hour(counterDown)
	occurrenceUp()
	schedule("0 0 2 * * ?", occurrenceSet)
}

def setCounter(number) {
	sendEvent(name: "counter", value: number)
}

def setOccurrences(number) {
	sendEvent(name: "occurrences", value: number)
}

def counterUp() {
	def counter = device.latestValue("counter") as Integer ?: 0
	if (counter < hours) {
		counter = counter + 1
		setCounter(counter)
	}
	if (counter > 0) {
	sendEvent(name: "switch", value: "off")
	} else {
	sendEvent(name: "switch", value: "on")
	}
}

def counterDown() {
	def counter = device.latestValue("counter") as Integer ?: 0
	if (counter > 0) {
		counter = counter - 1
		setCounter(counter)
	}
	if (counter < 1) {
	sendEvent(name: "switch", value: "on")
	} else {
	sendEvent(name: "switch", value: "off")
	}
}

def occurrenceUp() {
	def occurrences = device.latestValue("occurrences") as Integer ?: 0
	occurrences = occurrences + 1
	setOccurrences(occurrences)
}
def occurrenceSet() {
	sendEvent(name: "occurrences", value: 0)
}