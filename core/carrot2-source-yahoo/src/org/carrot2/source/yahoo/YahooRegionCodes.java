package org.carrot2.source.yahoo;

import org.carrot2.util.attribute.constraint.ValueHintMapping;

/**
 * Yahoo region codes.
 * 
 * http://developer.yahoo.com/search/regions.html
 */
public enum YahooRegionCodes implements ValueHintMapping
{
    ARGENTINA("Argentina", "ar"),
    AUSTRALIA("Australia", "au"),
    AUSTRIA("Austria", "at"),
    BRAZIL("Brazil", "br"),
    CANADA("Canada", "ca"),
    CATALAN("Catalan", "ct"),
    DENMARK("Denmark", "dk"),
    FINLAND("Finland", "fi"),
    FRANCE("France", "fr"),
    GERMANY("Germany", "de"),
    INDIA("India", "in"),
    INDONESIA("Indonesia", "id"),
    ITALY("Italy", "it"),
    MALAYSIA("Malaysia", "my"),
    MEXICO("Mexico", "mx"),
    NETHERLANDS("Netherlands", "nl"),
    NORWAY("Norway", "no"),
    PHILLIPINES("Phillipines", "ph"),
    RUSSIA("Russian Federation", "ru"),
    SINGAPORE("Singapore", "sg"),
    SPAIN("Spain", "es"),
    SWEDEN("Sweden", "se"),
    SWITZERLAND("Switzerland", "ch"),
    THAILAND("Thailand", "th"),
    UK("United Kingdom and Ireland", "uk"),
    USA("United States", "us");

    public final String friendlyName;
    public final String paramCode;
    
    private YahooRegionCodes(String friendlyName, String paramCode)
    {
        this.friendlyName = friendlyName;
        this.paramCode = paramCode;
    }
    
    public String getAttributeValue()
    {
        return paramCode;
    }

    public String getUserFriendlyName()
    {
        return friendlyName;
    }
    
    @Override
    public String toString()
    {
        return getUserFriendlyName();
    }
}
