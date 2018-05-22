package asset;

import java.io.File;

public class Asset<T>
{
    private T internal_asset;

    private Asset()
    {

    }

    public T getAsset()
    {
        return internal_asset;
    }

    public static Asset<File> FromFile(File file)
    {
        Asset ast = new Asset();
        ast.internal_asset = file;

        return ast;
    }
}