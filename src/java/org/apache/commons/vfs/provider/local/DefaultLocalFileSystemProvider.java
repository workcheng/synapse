/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software License
 * version 1.1, a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.apache.commons.vfs.provider.local;

import java.io.File;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.provider.AbstractFileSystemProvider;
import org.apache.commons.vfs.provider.DefaultFileName;
import org.apache.commons.vfs.FileSystem;
import org.apache.commons.vfs.provider.LocalFileProvider;
import org.apache.commons.vfs.provider.ParsedUri;
import org.apache.commons.vfs.util.Os;

/**
 * A file system provider, which uses direct file access.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 * @version $Revision: 1.3 $ $Date: 2002/07/05 04:08:18 $
 *
 * @ant.type type="file-provider" name="file"
 *
 */
public final class DefaultLocalFileSystemProvider
    extends AbstractFileSystemProvider
    implements LocalFileProvider
{
    private final LocalFileNameParser parser;

    public DefaultLocalFileSystemProvider()
    {
        if ( Os.isFamily( Os.OS_FAMILY_WINDOWS ) )
        {
            parser = new WindowsFileNameParser();
        }
        else
        {
            parser = new GenericFileNameParser();
        }
    }

    /**
     * Determines if a name is an absolute file name.
     */
    public boolean isAbsoluteLocalName( final String name )
    {
        return parser.isAbsoluteName( name );
    }

    /**
     * Finds a local file, from its local name.
     */
    public FileObject findLocalFile( final String name )
        throws FileSystemException
    {
        // TODO - tidy this up, no need to turn the name into an absolute URI,
        // and then straight back again
        return findFile( null, "file:" + name );
    }

    /**
     * Finds a local file.
     */
    public FileObject findLocalFile( final File file )
        throws FileSystemException
    {
        // TODO - tidy this up, should build file object straight from the file
        return findFile( null, "file:" + file.getAbsolutePath() );
    }

    /**
     * Parses a URI into its components.  The returned value is used to
     * locate the file system in the cache (using the root prefix), and is
     * passed to {@link #createFileSystem} to create the file system.
     *
     * <p>The provider can annotate this object with any additional
     * information it requires to create a file system from the URI.
     */
    protected ParsedUri parseUri( final FileObject baseFile,
                                  final String uri )
        throws FileSystemException
    {
        return parser.parseFileUri( uri );
    }

    /**
     * Creates the filesystem.
     */
    protected FileSystem createFileSystem( final ParsedUri uri )
        throws FileSystemException
    {
        // Build the name of the root file.
        final ParsedFileUri fileUri = (ParsedFileUri)uri;
        final String rootFile = fileUri.getRootFile();

        // Create the file system
        final DefaultFileName rootName = new DefaultFileName( parser, fileUri.getRootUri(), "/" );
        return new LocalFileSystem( rootName, rootFile );
    }
}
