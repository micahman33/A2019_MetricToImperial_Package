package com.automationanywhere.botcommand.metrictoimperial.commands;

import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;

import static com.automationanywhere.commandsdk.model.DataType.NUMBER;

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "CM to Inch", label = "[[CMtoINCH.label]]",
        node_label = "[[CMtoINCH.node_label]]",  description = "[[CMtoINCH.description]]", icon = "ruler_icon.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[CMtoINCH.return_label]]", return_type = NUMBER, return_required = true)
public class CMtoINCH {
    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute
    public NumberValue action(
            //Idx 1 would be displayed first, with a text box for entering the value.
            @Idx(index = "1", type = AttributeType.NUMBER)
            //UI labels.
            @Pkg(label = "[[CMtoINCH.CMInput.label]]")
            //Ensure that a validation error is thrown when the value is null.
            @NotEmpty
                    Double CMInput) {

        //Internal validation, to disallow empty inputs. No null check needed as we have NotEmpty on CMInput.
        if ("".equals(CMInput.toString().trim()))
            throw new BotCommandException("Input of CM is required");
        Number result;
        try {
            //Conversion logic
            result = CMInput * 0.393701;
        } catch (Exception e) {
            //Throw custom error message
            throw new BotCommandException("Unable to convert " + CMInput.toString() + "cm to inches");
        }
        //Return NumberValue.
        return new NumberValue(result);
    }
}
