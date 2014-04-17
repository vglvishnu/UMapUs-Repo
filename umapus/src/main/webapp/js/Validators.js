var Validators = function()
{
    var self = this;
    
    self.isValidEmail = function(emailaddress)
    {
        if(emailaddress === undefined || emailaddress == null || emailaddress.trim().length == 0)
          return false;
        else
        {
            var checkat = emailaddress.indexOf("@");
            var checkdot = emailaddress.lastIndexOf(".");
            var checkdoubledot = emailaddress.indexOf("..");
            var checkdoubleat = emailaddress.indexOf("@@");
            
            if(checkat == -1 || checkdot == -1 || checkdoubledot != -1 || checkdoubleat != -1 )
                return false;
            if(checkdot < checkat)
                return false;
            if(checkdot == emailaddress.length-1)
                return false;    
        }
        var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
       return filter.test(emailaddress);
    };
    
    self.isNumber = function(number)
    {
        if(number === undefined || number == null)
          return false;
        if(isNaN(number))
           return false;
        return true;
    };
    
    self.isNumberinRange = function(val,minval,maxval,isInclusive)
    {
        if(number === undefined || number == null)
          return false;
        if(isNaN(number))
           return false;
        if(isInclusive)
        {
            if(val >= minval && val <= maxval)
              return true;
            else
              return false;
        }
        else
        {
            if(val > minval && val < maxval)
              return true;
            else
              return false;
        }
    };

    self.isStringEmpty = function(val)
    {
        if(val === undefined || val == null || val.trim().length == 0)
           return true;
        else
           return false;
    };     

    self.isValidDate = function(dateval,formattype)
    {
        if(formattype != 'MM/DD/YYYY' && formattype == 'MM-DD-YYYY')
        {
            console.log("Invalid date format ignoring check and returning false");
            return false;
        }
        if(formattypep == 'MM/DD/YYYY')
        {
            try
            {
                if(dateval.split("/").length != 3)
                    return false;
                else
                {
                    var arr = dateval.split("/");
                    var month = arr[0];
                    var day = arr[1];
                    var year = arr[2];
                    if((month < 1) || (month > 12)) return false;
                    else if((day < 1) || (day > 31)) return false;
                    else if(((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day > 30)) return false;
                    else if((month == 2) && (((year % 400) == 0) || ((year % 4) == 0)) && ((year % 100) != 0) && (day > 29)) return false;
                    else if((month == 2) && ((year % 100) == 0) && (day > 29)) return false;
                    else return true;
                    
                }
            }catch(e)
            {
                console.log(e.message);
                return false;
            }
        }
        if(formattypep == 'MM-DD-YYYY')
        {
            try
            {
                if(dateval.split("-").length != 3)
                    return false;
                else
                {
                    var arr = dateval.split("-");
                    var month = arr[0];
                    var day = arr[1];
                    var year = arr[2];
                    if((month < 1) || (month > 12)) return false;
                    else if((day < 1) || (day > 31)) return false;
                    else if(((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day > 30)) return false;
                    else if((month == 2) && (((year % 400) == 0) || ((year % 4) == 0)) && ((year % 100) != 0) && (day > 29)) return false;
                    else if((month == 2) && ((year % 100) == 0) && (day > 29)) return false;
                    else return true;
                    
                }
            }catch(e)
            {
                console.log(e.message);
                return false;
            }
        }
    } ;
 
    self.toDate = function(strval)
    {
            if(strval.constructor === Date)
               return strval;
            else if(strval.constructor === Array)
               return new Date(strval[0],strval[1],strval[2]);
            else if(strval.constructor === Number)   
               return new Date(d);
            else if(strval.constructor === String)  
               return new Date(d);
            else if(typeof strval === "object" )
              return new Date(d.year,d.month,d.date);
            else  
              return NaN;
    };
    
    self.isDateinRange = function(valDate,minDate,maxDate,formattype)
    {
        var dateinput = toDate(valDate);
        var mindateinput = toDate(minDate);
        var maxdateinput = toDate(maxDate);
        if( dateinput === Date && mindateinput === Date && maxdateinput === Date)
        {
            if(dateinput >= mindateinput && dateinput <= maxdateinput)
                return true;
        }
        else
           return false;
        
    };
    

    
    
};

// Create a common validator object

var validator = new Validators();
