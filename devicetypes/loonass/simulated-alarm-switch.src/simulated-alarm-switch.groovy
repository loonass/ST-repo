metadata {

	definition (name: "Simulated Alarm Switch", namespace: "loonass", author: "Mike Harvey", runLocally: True) {
		capability "Switch"
	}

	tiles {
		standardTile("switch", "device.switch", width: 3, height: 2) {
			state "off", label: "Disarmed", action: "switch.on", icon: "st.Transportation.transportation13", backgroundColor: "#bc2323"
			state "on", label: "Armed", action: "switch.off", icon: "st.Transportation.transportation12", backgroundColor: "#44b621"
		}

		main "switch"
		details(["switch"])
	}
}

def on() {
	log.debug "Alarm on"
	sendEvent(name: "switch", value: "on")
}

def off() {
	log.debug "Alarm off"
	sendEvent(name: "switch", value: "off")
}