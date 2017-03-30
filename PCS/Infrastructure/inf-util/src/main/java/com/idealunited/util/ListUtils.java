package com.idealunited.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListUtils
{
	private ListUtils( )
	{}

	public static < T > List< T > removeAll( List< T > list, List< T > removeList )
	{
		if ( list == null || list.isEmpty( ) )
		{
			return Collections.emptyList( );
		}
		else if ( removeList == null || removeList.isEmpty( ) )
		{
			return new ArrayList< T >( list );
		}

		ArrayList< T > resultList = new ArrayList< T >( list.size( ) );
		for ( T element : list )
		{
			if ( !removeList.contains( element ) )
			{
				resultList.add( element );
			}
		}

		return resultList;
	}
}