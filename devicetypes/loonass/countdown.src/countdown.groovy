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

		command "counterUp"
		command "counterDown"

        attribute   "counter", "string"
	}

	tiles(scale: 2) {
			multiAttributeTile(name:"valueTile", type:"generic", canChangeIcon: true) {
				tileAttribute("device.counter", key: "PRIMARY_CONTROL") {
					attributeState "counter", label:'${currentValue}', icon: "st.Office.office6", backgroundColors:[
					[value: 0, color: "#00ff00"],
					[value: 8, color: "#ff0000"]
                    ]
					}
				tileAttribute("device.switch", key: "SECONDARY_CONTROL") {
					attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#00A0DC", nextState:"turningOff"
					attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
					attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.light.off", backgroundColor:"#00A0DC", nextState:"turningOff"
					attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.light.on", backgroundColor:"#ffffff", nextState:"turningOn"
                	}    
                tileAttribute("device.counter", key: "VALUE_CONTROL") {
					attributeState "VALUE_UP", action: "counterUp"
					attributeState "VALUE_DOWN", action: "counterDown"
					}
				}

		main(["valueTile"])
		details(["valueTile"])
	}
}

def installed() {
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
    sendEvent(name:"counter", value: 8)
    runEvery1Hour(counterDown)
}

def setCounter(percent) {
	sendEvent(name: "counter", value: percent)
}

def counterUp() {
	def counter = device.latestValue("counter") as Integer ?: 0
	if (counter < 8) {
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