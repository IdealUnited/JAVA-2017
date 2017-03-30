package com.idealunited.poss.utils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.idealunited.util.DateUtil;
import com.idealunited.util.StringUtil;

public final class MapUtils
{
	private MapUtils( )
	{}

	public static < T > Map< String, Object > objectToMap( T object )
	{
		if ( null == object )
		{
			return Collections.emptyMap( );
		}

		HashMap< String, Object > map = new HashMap< String, Object >( );

		BeanWrapper bean = new BeanWrapperImpl( object );
		PropertyDescriptor[ ] properties = bean.getPropertyDescriptors( );
		for ( PropertyDescriptor propertyDescriptor : properties )
		{
			String key = propertyDescriptor.getDisplayName( );
			Object value = bean.getPropertyValue( key );
			if ( null != value && !"class".equals( key ) )
			{
				map.put( key, value );
			}
		}

		return map;
	}

	public static < T > T map2Object( Class< T > targetClass, Map< String, Object > map )
			throws InstantiationException, IllegalAccessException
	{
		T targetObject = targetClass.newInstance( );

		if ( null == map )
		{
			return targetObject;
		}

		BeanWrapper bean = new BeanWrapperImpl( targetObject );
		for ( PropertyDescriptor propertyDescriptor : bean.getPropertyDescriptors( ) )
		{
			String key = propertyDescriptor.getDisplayName( );
			Object value = map.get( key );
			if ( null != value && bean.isWritableProperty( key ) )
			{
				Class< ? > propertyType = bean.getPropertyType( key );
				if ( propertyType.equals( String.class ) )
				{
					if ( !StringUtil.isEmpty( String.valueOf( value ) ) )
					{
						bean.setPropertyValue( key, String.valueOf( value ) );
					}
				}
				else if ( propertyType.equals( BigDecimal.class ) )
				{
					if ( !StringUtil.isEmpty( String.valueOf( value ) ) )
					{
						bean.setPropertyValue( key, new BigDecimal( value + "" ) );
					}
				}
				else if ( propertyType.equals( Long.class ) )
				{
					if ( !StringUtil.isEmpty( String.valueOf( value ) ) )
					{
						bean.setPropertyValue( key, Long.valueOf( value + "" ) );
					}
				}
				else if ( propertyType.equals( Date.class ) )
				{
					if ( value instanceof Long )
					{
						bean.setPropertyValue( key,
								DateUtil.parse( ( ( Long ) value ).longValue( ) ) );
					}
					else if ( value instanceof String )
					{
						String val = String.valueOf( value );
						switch ( val.length( ) )
						{
						case 19:
							bean.setPropertyValue( key,
									DateUtil.parse( DateUtil.DEFAULT_DATE_FROMAT, val ) );
							break;
						case 10:
							bean.setPropertyValue( key,
									DateUtil.parse( DateUtil.SIMPLE_DATE_FROMAT, val ) );
							break;
						case 14:
							bean.setPropertyValue( key, DateUtil.parse( DateUtil.PATTERN1, val ) );
							break;
						case 8:
							bean.setPropertyValue( key, DateUtil.parse( DateUtil.PATTERN, val ) );
							break;
						default:
							break;
						}
					}
					else
					{
						bean.setPropertyValue( key, value );
					}
				}
				else
				{
					bean.setPropertyValue( key, value );
				}
			}
		}
		return targetObject;
	}
}