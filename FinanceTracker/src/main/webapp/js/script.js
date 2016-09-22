/*
 * Copyright Shorif Ali (http://github.com/shorifali/)
 * Licensed under MIT (https://opensource.org/licenses/MIT)
 *
 */

'use strict';

$(document).ready(function() {

    // Default option
    var option = '<option value="0" selected="selected">Select Option</option>';

    // Method to clear dropdowns down to a given level
    var clearDropDown = function(arrayObj, startIndex) {
        $.each(arrayObj, function(index, value) {
            if(index >= startIndex) {
                $(value).html(option);
            }
        });
    };

    // Method to disable dropdowns down to a given level
    var disableDropDown = function(arrayObj, startIndex) {
        $.each(arrayObj, function(index, value) {
            if(index >= startIndex) {
                $(value).attr('disabled', 'disabled');
            }
        });
    };

    // Method to disable dropdowns down to a given level
    var enableDropDown = function(that) {
        that.removeAttr('disabled');
    };

    // Method to generate and append options
    var generateOptions = function(element, selection, limit) {
        var options = '';
            if (selection==1){
            options += '<option value="' + 1 + '">A3 '  + '</option>';
            options += '<option value="' + 1 + '">A4 '  + '</option>';
            options += '<option value="' + 1 + '">A6 '  + '</option>';

        element.append(options);
      } else {
        options += '<option value="' + 1 + '">3er Series '  + '</option>';
        options += '<option value="' + 1 + '">5er Series '  + '</option>';
        options += '<option value="' + 1 + '">7er Series '  + '</option>';
        element.append(options);
      }
    };

    // Select each of the dropdowns
    var brandDropDown = $('#brand');
    var modelDropDown = $('#model');
    var thirdDropDown = $('#third');

    // Hold selected option
    var brandSelection = '';
    var modelSelection = '';
    var thirdSelection = '';

    // Hold selection
    var selection = '';

    // Selection handler for brand level dropdown
    brandDropDown.on('change', function() {

        // Get selected option
        brandSelection = brandDropDown.val();

        // Clear all dropdowns down to the hierarchy
        clearDropDown($('select'), 1);

        // Disable all dropdowns down to the hierarchy
        disableDropDown($('select'), 1);

        // Check current selection
        if(brandSelection === '0') {
            return;
        }

        // Enable model level DropDown
        enableDropDown(modelDropDown);

        // Generate and append options
        selection = brandSelection;
        generateOptions(modelDropDown, selection, 4);
    });

    // Selection handler for model level dropdown
    modelDropDown.on('change', function() {
        modelSelection = modelDropDown.val();

        // Clear all dropdowns down to the hierarchy
        clearDropDown($('select'), 2);

        // Disable all dropdowns down to the hierarchy
        disableDropDown($('select'), 2);

        // Check current selection
        if(modelSelection === '0') {
             return;
        }

        // Enable third level DropDown
        enableDropDown(thirdDropDown);

        // Generate and append options
        selection = brandSelection + '-' + modelSelection;
        generateOptions(thirdDropDown, selection, 4);
    });

    // Selection handler for third level dropdown
    thirdDropDown.on('change', function() {
        thirdSelection = thirdDropDown.val();

        console.log(thirdSelection);
        // Final work goes here

     });

    /*
     * In this way we can make any number of dependent dropdowns
     *
     */

});
