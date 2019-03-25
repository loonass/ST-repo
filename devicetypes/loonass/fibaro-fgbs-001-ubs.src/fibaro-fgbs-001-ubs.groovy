/**
 *  Device Type Definition File
 *
 *  Device Type:		Fibaro UBS - Contact, Temperature and Tamper Sensor
 *  File Name:			fibaro-fgbs-001-ubs.groovy
 *	Initial Release:	18-03-2019
 *	Author:				Mike Harvey
 *
 ***************************************************************************************
 */
 
metadata {
	definition (name: "Fibaro FGBS-001 UBS", namespace: "loonass", author: "Mike Harvey") {
    
    capability "Configuration"
    capability "Contact Sensor"
    capability "Health Check"
 	capability "Motion Sensor"
    capability "Sensor"
	capability "Temperature Measurement"
    
    command "removeChildDevices"
    command "createChildDevices"
    command "createChildTempDevices"
    command "updateCurrentParams"
    command "listCurrentParams"
    
    attribute "contact1","enum",["open","close"]
    attribute "contact2","enum",["open","close"]
	
	fingerprint type: "2001", cc: "30 60 85 8E 72 70 86 7A", ccOut: "2B"
}

simulator {
}

tiles(scale: 2) {
	standardTile("contact1", "device.contact1", width: 4, height: 4) {
		state "open", label: '${name}', icon: "st.contact.contact.open", backgroundColor: "#e86d13"
		state "closed", label: '${name}', icon: "st.contact.contact.closed", backgroundColor: "#00a0dc"
	}
	standardTile("contact2", "device.contact2", width: 2, height: 2) {
		state "open", label:'tampered', icon:"st.locks.lock.unlocked", backgroundColor:"#e86d13"
		state "closed", label:'secure',    icon:"st.locks.lock.locked",   backgroundColor:"#00a0dc"
	}
	
	valueTile("temperature1", "temperature1", width:2, height:2, decoration: "flat", canChangeIcon: true) {
		state "default", label:'${currentValue}°', unit:"C", backgroundColors:[
							[value: 0, color: "#153591"],
							[value: 7, color: "#1e9cbb"],
							[value: 15, color: "#90d2a7"],
							[value: 23, color: "#44b621"],
							[value: 28, color: "#f1d801"],
							[value: 35, color: "#d04e00"],
							[value: 37, color: "#bc2323"]
                            ]
    }

	standardTile("configure", "device.configure", inactiveLabel: false, decoration: "flat", width: 3, height: 1) {
		state "default", label:'Send Config', action:"updateCurrentParams"//, icon:"st.secondary.configure"
	}
	standardTile("report", "device.report", inactiveLabel: false, decoration: "flat", width: 3, height: 1) {
		state "default", label:'List Config', action:"listCurrentParams"
	}
    
}

main(["contact1"]) //, "temperature1"
details([
        "contact1", "temperature1", "contact2"
        //"configure", "report"
        ])

preferences {
        input name: "Info", type: "paragraph", title:"Device Handler by @cjcharles", description: "Parameter Settings:", displayDuringSetup: false

        input name: "param1", type: "number", range: "0..65535", required: true, //defaultValue: "0",
            title: "Parameter No. 1 - Input 1 Alarm Cancellation Delay. \n" +
                   "Additional delay after an alarm from input 1 has ceased.\n" +
                   "Time in seconds to delay the ceasing event.\n" +
                   "Default value: 0."
       
        input name: "param2", type: "number", range: "0..65535", required: true, //defaultValue: "0",
            title: "Parameter No. 2 - Input 2 Alarm Cancellation Delay. \n" +
                   "Additional delay after an alarm from input 2 has ceased.\n" +
                   "Time in seconds to delay the ceasing event.\n" +
                   "Default value: 0."
       
        input name: "param3", type: "number", range: "0..3", required: true, //defaultValue: "1",
            title: "Parameter No. 3 - Type of Input No 1." +
                   "Available settings:\n" +
                   "0 – INPUT_NO (Normal Open)\n" +
				   "1 – INPUT_NC (Normal Close)\n" +
				   "2 – INPUT_MONOSTABLE\n" +
				   "3 – INPUT_BISTABLE\n" +
                   "Default value: 1."

		input name: "param4", type: "number", range: "0..3", required: true, //defaultValue: "1",
            title: "Parameter No. 4 - Type of Input No 2." +
                   "Available settings:\n" +
                   "0 – INPUT_NO (Normal Open)\n" +
				   "1 – INPUT_NC (Normal Close)\n" +
				   "2 – INPUT_MONOSTABLE\n" +
				   "3 – INPUT_BISTABLE\n" +
                   "Default value: 1."

		input name: "param5", type: "number", range: "0..255", required: true, //defaultValue: "255",
            title: "Parameter No. 5 - Type of transmitted control or alarm frame for association group 1." +
                   "Available settings:\n" +
				   "0 – Frame ALARM GENERIC\n" +
				   "1 – Frame ALARM SMOKE\n" +
				   "2 – Frame ALARM CO\n" +
				   "3 – Frame ALARM CO2\n" +
				   "4 – Frame ALARM HEAT\n" +
				   "5 – Frame ALARM WATER\n" +
				   "255 – Control frame BASIC_SET\n" +
                   "Default value: 255."

		input name: "param6", type: "number", range: "0..255", required: true, //defaultValue: "255",
            title: "Parameter No. 6 - Type of transmitted control or alarm frame for association group 2." +
                   "Available settings:\n" +
				   "0 – Frame ALARM GENERIC\n" +
				   "1 – Frame ALARM SMOKE\n" +
				   "2 – Frame ALARM CO\n" +
				   "3 – Frame ALARM CO2\n" +
				   "4 – Frame ALARM HEAT\n" +
				   "5 – Frame ALARM WATER\n" +
				   "255 – Control frame BASIC_SET\n" +
                   "Default value: 255."

		input name: "param7", type: "number", range: "0..255", required: true, //defaultValue: "255",
            title: "Parameter No. 7 - Value of the parameter specifying the forced level of dimming / opening sun blinds when " +
            	   "sent a “switch on” / ”open” command from association group no. 1.\n" +
                   "Available settings:\n" +
                   "0-99 - Dimming or Opening Percentage\n" +
                   "255 - Last set percentage\n" +
                   "Default value: 255."

		input name: "param8", type: "number", range: "0..255", required: true, //defaultValue: "255",
            title: "Parameter No. 8 - Value of the parameter specifying the forced level of dimming / opening sun blinds when " +
            	   "sent a “switch on” / ”open” command from association group no. 2.\n" +
                   "Available settings:\n" +
                   "0-99 - Dimming or Opening Percentage\n" +
                   "255 - Last set percentage\n" +
                   "Default value: 255."

		input name: "param9", type: "number", range: "0..3", required: true, //defaultValue: "0",
            title: "Parameter No. 9 - Deactivating transmission of the frame cancelling the alarm or the " +
				   "control frame deactivating the device (Basic). Disable the alarm cancellation function.\n" +
                   "Available settings:\n" +
                   "0 – Cancellation sent for association group 1 and 2\n" +
				   "1 – Cancellation sent for association group 1 only\n" +
				   "2 – Cancellation sent for association group 2 only\n" +
				   "3 - Not sent for association group 1 or 2\n" +
                   "Default value: 0."

		input name: "param10", type: "number", range: "1..255", required: true, //defaultValue: "20",
            title: "Parameter No. 10 - Interval between successive readings of temperature from all " +
				   "sensors connected to the device. (A reading does not result in sending to ST)\n" +
                   "Available settings:\n" +
                   "1-255 - Seconds between readings\n" +
                   "Default value: 20."

		input name: "param11", type: "number", range: "0..255", required: true, //defaultValue: "200",
            title: "Parameter No. 11 - Interval between forcing to send report of the temperature. " +
				   "The forced report is sent immediately after the next temperature reading, " +
				   "irrespective of parameter 12. Advised to be 200 unless rapid temperature changes are expected.\n" +
                   "Available settings:\n" +
                   "0 - Deactivate temperature sending\n" +
                   "1-255 - Seconds between sends\n" +
                   "Default value: 200."

		input name: "param12", type: "number", range: "0..255", required: true, //defaultValue: "8",
            title: "Parameter No. 12 - Insensitiveness to temperature changes. This is the maximum " +
				   "difference between the last reported temperature and the current temperature. " +
				   "If they differ by more than this then a report is sent.\n" +
                   "Available settings:\n" +
                   "0-255 - x/16 = temp diff in C\n" +
                   "x/80*9 = temp diff in F\n" +
                   "Default value: 8 (0.5oC)."

		input name: "param13", type: "number", range: "0..3", required: true, //defaultValue: "0",
            title: "Parameter No. 13 - Transmitting the alarm or control frame in “broadcast” mode (i.e. to " +
				   "all devices within range), this information is not repeated by the mesh network." +
                   "Available settings:\n" +
                   "0 - IN1 and IN2 broadcast inactive,\n" +
                   "1 - IN1 broadcast mode active only,\n" +
                   "2 - IN2 broadcast mode active only,\n" +
                   "3 - INI and IN2 broadcast mode active.\n" +
                   "Default value: 0."

		input name: "param14", type: "number", range: "0..1", required: true, //defaultValue: "0",
            title: "Parameter No. 14 - Scene activation functionality." +
                   "Available settings:\n" +
                   "0 - Deactivated functionality,\n" +
                   "1 - Activated functionality.\n" +
                   "Default value: 0."
	}
}

def installed() {
	log.debug "installed()"
}
def updated() {
	log.debug "updated()"
	log.debug "Dont forget to press the button to send config to the device"
    //configure()

}
def uninstalled() {
    log.debug "uninstalled()"
}

def configure() {
	log.debug "configure()"
    updateCurrentParams()
}

def parse(String description) {
	def result = null
	def cmd = zwave.parse(description, [ 0x60: 3])
	if (cmd) {
		result = zwaveEvent(cmd)
	}
	log.debug "parsed '$description' to result: ${result}"
	return result
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv1.ManufacturerSpecificReport cmd) {
	log.debug("ManufacturerSpecificReport ${cmd.inspect()}")
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
	log.debug("ConfigurationReport ${cmd.inspect()}")
}

def createEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd, Map item1) { 
	log.debug "manufacturerId:   ${cmd.manufacturerId}"
    log.debug "manufacturerName: ${cmd.manufacturerName}"
    log.debug "productId:        ${cmd.productId}"
    log.debug "productTypeId:    ${cmd.productTypeId}"

}

def createEvent(physicalgraph.zwave.commands.versionv1.VersionReport cmd, Map item1) {	
    updateDataValue("applicationVersion", "${cmd.applicationVersion}")
    log.debug "applicationVersion:      ${cmd.applicationVersion}"
    log.debug "applicationSubVersion:   ${cmd.applicationSubVersion}"
    log.debug "zWaveLibraryType:        ${cmd.zWaveLibraryType}"
    log.debug "zWaveProtocolVersion:    ${cmd.zWaveProtocolVersion}"
    log.debug "zWaveProtocolSubVersion: ${cmd.zWaveProtocolSubVersion}"
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	log.debug "BasicSet V1 ${cmd.inspect()}"
    def currentstate
	if (cmd.value) {
		currentstate = "open"
	} else {
    	currentstate = "closed"
	}
    createEvent(name: "contact1", value: currentstate, descriptionText: "${device.displayName} is ${currentstate}")
    
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelCmdEncap cmd) {
	log.debug "ZWaveEvent V3 ${cmd.inspect()}"
	def result
	if (cmd.commandClass == 32) {
        def currentstate
		if (cmd.parameter == [0]) {
        	currentstate = "closed"
		}
		if (cmd.parameter == [255]) {
        	currentstate = "open"
		}
        log.debug "ep${cmd.sourceEndPoint} is ${currentstate}"
        //First update tile on this device
        sendEvent(name: "contact${cmd.sourceEndPoint}", value: currentstate, descriptionText: "$device.displayName - ep${cmd.sourceEndPoint} is ${currentstate}")
		//If not null then we have found either ep1 or ep2, hence try to send to the child device aswell
        
    }
	else if (cmd.commandClass == 49) {
		if ((cmd.sourceEndPoint >= 3) && (cmd.sourceEndPoint <= 6)) {
            
			def tempsensorid = cmd.sourceEndPoint - 2
			def tempendpoint = "temperature" + tempsensorid.toString()
			
            def tempval = ((cmd.parameter[4] * 256) + cmd.parameter[5])
            if (tempval > 32767) {
            	//Here we deal with negative values
            	tempval = tempval - 65536
            }
            //Finally round the temperature
            def tempprocessed = (tempval / 100).toDouble().round(1)
            
			//def cmdScale = cmd.scale == 1 ? "F" : "C"
			//def tempval = convertTemperatureIfNeeded(cmd.scaledSensorValue, cmdScale, cmd.precision).toDouble().round(1)
            
            log.debug "${tempendpoint} has changed to ${tempprocessed}"
            
            sendEvent(name: tempendpoint, value: tempprocessed, displayed: true) //unit: getTemperatureScale()
            
			
        }
	}
	else {
		//Send them here just in case we want to do more complicated processing (not doing it as need to have endpoint passed and that makes it a bit messy)
		def encapsulatedCommand = cmd.encapsulatedCommand([0x31: 2, 0x60: 3, 0x85: 2, 0x8E: 2, 0x72: 1, 0x70: 1, 0x86: 1, 0x7A: 1, 0xEF: 1, 0x2B: 1]) // can specify command class versions here like in zwave.parse
		log.debug ("Command from endpoint ${cmd.sourceEndPoint}: ${encapsulatedCommand}")
		if (encapsulatedCommand) {
			result = zwaveEvent(encapsulatedCommand)
		}
	}
    return result
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv2.SensorMultilevelReport cmd)
{
	//This is no longer used as caught in an earlier event, but kept here in case the code is useful
	log.debug "Sensor MultiLevel Report - Sensor Type = ${cmd.sensorType}"
	switch (cmd.sensorType) {
		case 1:
			// temperature
			def cmdScale = cmd.scale == 1 ? "F" : "C"
			def tempval = convertTemperatureIfNeeded(cmd.scaledSensorValue, cmdScale, cmd.precision).toDouble().round(1)
            sendEvent(name: "temperature1", value: tempval, displayed: false) //unit: getTemperatureScale()
			break;
	}
    log.debug map
	createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv1.SensorMultilevelReport cmd) {
    log.debug "SensorMultilevelReport $cmd"
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	// This will capture any commands not handled by other instances of zwaveEvent
	// and is recommended for development so you can see every command the device sends
	log.debug "Catchall reached for cmd: ${cmd.toString()}}"
	return createEvent(descriptionText: "${device.displayName}: ${cmd}")
}

def updateCurrentParams() {
	log.debug "Sending configuration parameters to device"
    def cmds = []
	cmds << zwave.multiChannelAssociationV2.multiChannelAssociationSet(groupingIdentifier:2, nodeId:[zwaveHubNodeId]).format()
	cmds << zwave.associationV2.associationSet(groupingIdentifier:3, nodeId:[zwaveHubNodeId]).format()
	cmds << zwave.associationV1.associationRemove(groupingIdentifier:1, nodeId:zwaveHubNodeId).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 1, configurationValue:[param1.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 2, configurationValue:[param2.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 3, configurationValue:[param3.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 4, configurationValue:[param4.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 5, configurationValue:[param5.value]).format()
    cmds << zwave.configurationV1.configurationSet(parameterNumber: 6, configurationValue:[param6.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 7, configurationValue:[param7.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 8, configurationValue:[param8.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 9, configurationValue:[param9.value]).format()
    cmds << zwave.configurationV1.configurationSet(parameterNumber: 10, configurationValue:[param10.value]).format()
    cmds << zwave.configurationV1.configurationSet(parameterNumber: 11, configurationValue:[param11.value]).format()
    cmds << zwave.configurationV1.configurationSet(parameterNumber: 12, configurationValue:[param12.value]).format()
	cmds << zwave.configurationV1.configurationSet(parameterNumber: 13, configurationValue:[param13.value]).format()
    cmds << zwave.configurationV1.configurationSet(parameterNumber: 14, configurationValue:[param14.value]).format()
	delayBetween(cmds, 500)
}
def listCurrentParams() {
	log.debug "Listing of current parameter settings of ${device.displayName}"
    def cmds = []
	cmds << zwave.multiChannelAssociationV2.multiChannelAssociationGet(groupingIdentifier:2).format()
	cmds << zwave.associationV2.associationGet(groupingIdentifier: 3).format()
	cmds << zwave.associationV1.associationGet(groupingIdentifier: 1).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 1).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 2).format()
   	cmds << zwave.configurationV1.configurationGet(parameterNumber: 3).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 4).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 5).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 6).format()
    cmds << zwave.configurationV1.configurationGet(parameterNumber: 7).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 8).format()
   	cmds << zwave.configurationV1.configurationGet(parameterNumber: 9).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 10).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 11).format()
   	cmds << zwave.configurationV1.configurationGet(parameterNumber: 12).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 13).format()
	cmds << zwave.configurationV1.configurationGet(parameterNumber: 14).format()
	delayBetween(cmds, 500)
}

def ping() {
	return zwave.switchMultilevelV2.switchMultilevelGet().format()
    log.debug "ping"
}