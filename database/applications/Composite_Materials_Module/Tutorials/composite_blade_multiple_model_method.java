/*
 * composite_blade_multiple_model_method.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:34 by COMSOL 6.3.0.290. */
public class composite_blade_multiple_model_method {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);

    model.param().set("th", "1.4[mm]");
    model.param().descr("th", "\u5c42\u539a\u5ea6");
    model.param().set("thc", "2[cm]");
    model.param().descr("thc", "\u82af\u5c42\u539a\u5ea6");
    model.param().set("mn", "3");
    model.param().descr("mn", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("p0", "1e4[Pa]");
    model.param().descr("p0", "\u8f7d\u8377\u5927\u5c0f");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6750\u6599\uff1a\u78b3\u7ea4-\u73af\u6c27");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1aCE-[0]");
    model.material("lmat1").setIndex("thickness", "th*4", 0);
    model.material("lmat1").setIndex("meshPoints", 4, 0);
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u6750\u6599\uff1a\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f");
    model.material().create("lmat2", "LayeredMaterial", "");
    model.material("lmat2").label("\u591a\u5c42\u6750\u6599\uff1aGV-[0/45/-45/90]_s");
    model.material("lmat2").setIndex("link", "mat2", 0);
    model.material("lmat2").setIndex("thickness", "th", 0);
    model.material("lmat2").setIndex("meshPoints", 1, 0);
    model.material("lmat2").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat2").setIndex("link", "mat2", 1);
    model.material("lmat2").setIndex("rotation", "0.0", 1);
    model.material("lmat2").setIndex("thickness", "th", 1);
    model.material("lmat2").setIndex("meshPoints", 1, 1);
    model.material("lmat2").setIndex("tag", "lmat2_2", 1);
    model.material("lmat2").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat2").setIndex("link", "mat2", 1);
    model.material("lmat2").setIndex("rotation", "0.0", 1);
    model.material("lmat2").setIndex("thickness", "th", 1);
    model.material("lmat2").setIndex("meshPoints", 1, 1);
    model.material("lmat2").setIndex("tag", "lmat2_2", 1);
    model.material("lmat2").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat2").setIndex("link", "mat2", 2);
    model.material("lmat2").setIndex("rotation", "0.0", 2);
    model.material("lmat2").setIndex("thickness", "th", 2);
    model.material("lmat2").setIndex("meshPoints", 1, 2);
    model.material("lmat2").setIndex("tag", "lmat2_3", 2);
    model.material("lmat2").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat2").setIndex("link", "mat2", 2);
    model.material("lmat2").setIndex("rotation", "0.0", 2);
    model.material("lmat2").setIndex("thickness", "th", 2);
    model.material("lmat2").setIndex("meshPoints", 1, 2);
    model.material("lmat2").setIndex("tag", "lmat2_3", 2);
    model.material("lmat2").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat2").setIndex("link", "mat2", 3);
    model.material("lmat2").setIndex("rotation", "0.0", 3);
    model.material("lmat2").setIndex("thickness", "th", 3);
    model.material("lmat2").setIndex("meshPoints", 1, 3);
    model.material("lmat2").setIndex("tag", "lmat2_4", 3);
    model.material("lmat2").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat2").setIndex("link", "mat2", 3);
    model.material("lmat2").setIndex("rotation", "0.0", 3);
    model.material("lmat2").setIndex("thickness", "th", 3);
    model.material("lmat2").setIndex("meshPoints", 1, 3);
    model.material("lmat2").setIndex("tag", "lmat2_4", 3);
    model.material("lmat2").setIndex("layername", "\u5c42 5", 4);
    model.material("lmat2").setIndex("link", "mat2", 4);
    model.material("lmat2").setIndex("rotation", "0.0", 4);
    model.material("lmat2").setIndex("thickness", "th", 4);
    model.material("lmat2").setIndex("meshPoints", 1, 4);
    model.material("lmat2").setIndex("tag", "lmat2_5", 4);
    model.material("lmat2").setIndex("layername", "\u5c42 5", 4);
    model.material("lmat2").setIndex("link", "mat2", 4);
    model.material("lmat2").setIndex("rotation", "0.0", 4);
    model.material("lmat2").setIndex("thickness", "th", 4);
    model.material("lmat2").setIndex("meshPoints", 1, 4);
    model.material("lmat2").setIndex("tag", "lmat2_5", 4);
    model.material("lmat2").setIndex("layername", "\u5c42 6", 5);
    model.material("lmat2").setIndex("link", "mat2", 5);
    model.material("lmat2").setIndex("rotation", "0.0", 5);
    model.material("lmat2").setIndex("thickness", "th", 5);
    model.material("lmat2").setIndex("meshPoints", 1, 5);
    model.material("lmat2").setIndex("tag", "lmat2_6", 5);
    model.material("lmat2").setIndex("layername", "\u5c42 6", 5);
    model.material("lmat2").setIndex("link", "mat2", 5);
    model.material("lmat2").setIndex("rotation", "0.0", 5);
    model.material("lmat2").setIndex("thickness", "th", 5);
    model.material("lmat2").setIndex("meshPoints", 1, 5);
    model.material("lmat2").setIndex("tag", "lmat2_6", 5);
    model.material("lmat2").setIndex("layername", "\u5c42 7", 6);
    model.material("lmat2").setIndex("link", "mat2", 6);
    model.material("lmat2").setIndex("rotation", "0.0", 6);
    model.material("lmat2").setIndex("thickness", "th", 6);
    model.material("lmat2").setIndex("meshPoints", 1, 6);
    model.material("lmat2").setIndex("tag", "lmat2_7", 6);
    model.material("lmat2").setIndex("layername", "\u5c42 7", 6);
    model.material("lmat2").setIndex("link", "mat2", 6);
    model.material("lmat2").setIndex("rotation", "0.0", 6);
    model.material("lmat2").setIndex("thickness", "th", 6);
    model.material("lmat2").setIndex("meshPoints", 1, 6);
    model.material("lmat2").setIndex("tag", "lmat2_7", 6);
    model.material("lmat2").setIndex("layername", "\u5c42 8", 7);
    model.material("lmat2").setIndex("link", "mat2", 7);
    model.material("lmat2").setIndex("rotation", "0.0", 7);
    model.material("lmat2").setIndex("thickness", "th", 7);
    model.material("lmat2").setIndex("meshPoints", 1, 7);
    model.material("lmat2").setIndex("tag", "lmat2_8", 7);
    model.material("lmat2").setIndex("layername", "\u5c42 8", 7);
    model.material("lmat2").setIndex("link", "mat2", 7);
    model.material("lmat2").setIndex("rotation", "0.0", 7);
    model.material("lmat2").setIndex("thickness", "th", 7);
    model.material("lmat2").setIndex("meshPoints", 1, 7);
    model.material("lmat2").setIndex("tag", "lmat2_8", 7);
    model.material("lmat2").setIndex("rotation", 45, 1);
    model.material("lmat2").setIndex("rotation", -45, 2);
    model.material("lmat2").setIndex("rotation", 90, 3);
    model.material("lmat2").setIndex("rotation", 90, 4);
    model.material("lmat2").setIndex("rotation", -45, 5);
    model.material("lmat2").setIndex("rotation", 45, 6);
    model.material("lmat2").setIndex("rotation", 0, 7);
    model.material("lmat2").set("widthRatio", 0.6);
    model.material().create("mat3", "Common", "");
    model.material("mat3").label("\u6750\u6599\uff1aPVC \u6ce1\u6cab");
    model.material().create("lmat3", "LayeredMaterial", "");
    model.material("lmat3").label("\u591a\u5c42\u6750\u6599\uff1aPF-[0]");
    model.material("lmat3").setIndex("link", "mat3", 0);
    model.material("lmat3").setIndex("thickness", "thc", 0);
    model.material("lmat3").setIndex("meshPoints", 10, 0);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("coord2", new double[]{0, 0.4});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 2, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", 2, 0, 1);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("twist", 30, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").feature("stllmat1")
         .label("\u78b3\u7ea4-\u73af\u6c27\u6811\u8102-1 [0]");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat2", "stllmat1");
    model.component("comp1").material("stlmat1").feature("stllmat2")
         .label("\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f-1 [0/45/-45/90]_s");
    model.component("comp1").material("stlmat1").feature("stllmat2").set("link", "lmat2");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat3", "stllmat2");
    model.component("comp1").material("stlmat1").feature("stllmat3").label("PVC \u6ce1\u6cab [0]");
    model.component("comp1").material("stlmat1").feature("stllmat3").set("link", "lmat3");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat4", "stllmat3");
    model.component("comp1").material("stlmat1").feature("stllmat4")
         .label("\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f-2 [0/45/-45/90]_s");
    model.component("comp1").material("stlmat1").feature("stllmat4").set("link", "lmat2");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat5", "stllmat4");
    model.component("comp1").material("stlmat1").feature("stllmat5")
         .label("\u78b3\u7ea4-\u73af\u6c27\u6811\u8102-2 [0]");
    model.component("comp1").material("stlmat1").feature("stllmat5").set("link", "lmat1");
    model.component("comp1").material("stlmat1").set("widthRatio", 0.4);

    model.component("comp1").physics("lshell").label("\u591a\u5c42\u58f3\uff08\u591a\u6a21\u578b\u65b9\u6cd5\uff09");
    model.component("comp1").physics("lshell").prop("LayerSelection").set("allLayers", false);
    model.component("comp1").physics("lshell").prop("LayerSelection")
         .set("shelllist_lCheck", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("lshell").prop("LayerSelection").setIndex("shelllist_lCheck", 1, 0, 0);
    model.component("comp1").physics("lshell").prop("LayerSelection").setIndex("shelllist_lCheck", 1, 9, 0);
    model.component("comp1").physics("lshell").prop("LayerSelection").setIndex("shelllist_lCheck", 1, 18, 0);
    model.component("comp1").physics("lshell").feature("lemm1").set("TransverseIsotropic", true);
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().set(1);
    model.component("comp1").physics("lshell").feature("fix1").set("allLayers", true);
    model.component("comp1").physics("lshell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("lshell").feature("fl1").label("\u9762\u8f7d\u8377\uff0c\u9876\u90e8");
    model.component("comp1").physics("lshell").feature("fl1").selection().set(1);
    model.component("comp1").physics("lshell").feature("fl1").set("applyTo", "top");
    model.component("comp1").physics("lshell").feature("fl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-p0*exp(-j*mn*atan2(Y,X))"});
    model.component("comp1").physics("lshell").feature().duplicate("fl2", "fl1");
    model.component("comp1").physics("lshell").feature("fl2").label("\u9762\u8f7d\u8377\uff0c\u5e95\u90e8");
    model.component("comp1").physics("lshell").feature("fl2").set("applyTo", "bottom");
    model.component("comp1").physics("shell").label("\u58f3 1\uff08\u591a\u6a21\u578b\u65b9\u6cd5\uff09");
    model.component("comp1").physics("shell").prop("ShellAdvancedSettings").set("UseMITCInterp", false);
    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").selection().set(1);
    model.component("comp1").physics("shell").feature("llem1").set("allLayers", false);
    model.component("comp1").physics("shell").feature("llem1").set("stack", "stlmat1@stllmat2");
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").set("TransverseIsotropic", true);
    model.component("comp1").physics("shell").feature("llem1").set("ShearCorrectionFactor", "UserDefined");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(1);
    model.component("comp1").physics().copy("shell2", "shell");
    model.component("comp1").physics("shell2").label("\u58f3 2\uff08\u591a\u6a21\u578b\u65b9\u6cd5\uff09");
    model.component("comp1").physics("shell2").feature("llem1").set("stack", "stlmat1@stllmat4");

    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"139e9", "9e9"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.32", "0.32"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"5.5e9"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"1560"});
    model.material("mat2").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"41e9", "9e9"});
    model.material("mat2").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.3", "0.3"});
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"4.1e9"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"1890"});
    model.material("mat3").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat3").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"250e6", "250e6"});
    model.material("mat3").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.35", "0.35"});
    model.material("mat3").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"92.6e6"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"200"});

    model.component("comp1").multiphysics().create("lssh1", "LayeredShellShellConnection", 2);
    model.component("comp1").multiphysics().duplicate("lssh2", "lssh1");
    model.component("comp1").multiphysics("lssh2").set("Shell_physics", "shell2");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1")
         .label("\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387\uff08\u591a\u6a21\u578b\u65b9\u6cd5\uff09");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b\uff08\u591a\u6a21\u578b\u65b9\u6cd5\uff09");
    model.result("pg1").set("data", "lshl1");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "shell.disp");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("expr", "shell2.disp");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("def1").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{3});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{5});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{6});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1});
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/lshell", true);
    model.study("std2").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std2").feature("freq").setSolveFor("/physics/shell2", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/lssh1", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/lssh2", true);
    model.study("std2").feature("freq").set("plist", 10);
    model.study("std2").label("\u7814\u7a76\uff1a\u9891\u7387\uff08\u591a\u6a21\u578b\u65b9\u6cd5\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("lshl2", "LayeredMaterial");
    model.result().dataset("lshl2").set("data", "dset2");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("Mises \u5cf0\u503c\u5e94\u529b");
    model.result("pg2").set("data", "lshl2");
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "lshell.mises_peak");
    model.result("pg2").feature("surf1").set("unit", "GPa");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "shell.mises_peak");
    model.result("pg2").feature().duplicate("surf3", "surf2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature("def1").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("expr", "shell2.mises_peak");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb\uff0c\u5207\u9762");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("view", "view4");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("locdef", "relative");
    model.result("pg3").feature("lss1").set("localzrel", 1);
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics().copy("lshell2", "lshell");
    model.component("comp1").physics("lshell2").label("\u591a\u5c42\u58f3\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.component("comp1").physics("lshell2").prop("LayerSelection").set("allLayers", true);

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/lshell", true);
    model.study("std3").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std3").feature("eig").setSolveFor("/physics/shell2", true);
    model.study("std3").feature("eig").setSolveFor("/physics/lshell2", true);
    model.study("std3").feature("eig").setSolveFor("/multiphysics/lssh1", true);
    model.study("std3").feature("eig").setSolveFor("/multiphysics/lssh2", true);
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig").setSolveFor("/physics/lshell", false);
    model.study("std3").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std3").feature("eig").setSolveFor("/physics/shell2", false);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"lshell", "shell", "shell2"});
    model.study("std3").feature("eig").setSolveFor("/multiphysics/lssh1", false);
    model.study("std3").feature("eig").setSolveFor("/multiphysics/lssh2", false);
    model.study("std3").feature("eig").set("disabledcoupling", new String[]{"lssh1", "lssh2"});
    model.study("std3").label("\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().duplicate("lshl3", "lshl1");
    model.result().dataset("lshl3").set("data", "dset3");
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("\u632f\u578b\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.result("pg4").set("data", "none");
    model.result("pg4").run();
    model.result("pg4").feature().remove("surf2");
    model.result("pg4").feature().remove("surf3");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "lshell2.disp");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"u4", "v4", "w4"});
    model.result("pg4").run();
    model.result("pg4").set("data", "lshl3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "lshell2.disp");
    model.result("pg4").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().move("pg4", 1);

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/lshell", true);
    model.study("std4").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std4").feature("freq").setSolveFor("/physics/shell2", true);
    model.study("std4").feature("freq").setSolveFor("/physics/lshell2", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/lssh1", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/lssh2", true);
    model.study("std4").feature("freq").set("plist", 10);
    model.study("std4").feature("freq").set("useadvanceddisable", true);
    model.study("std4").feature("freq").setSolveFor("/physics/lshell", false);
    model.study("std4").feature("freq").setSolveFor("/physics/shell", false);
    model.study("std4").feature("freq").setSolveFor("/physics/shell2", false);
    model.study("std4").feature("freq").set("disabledphysics", new String[]{"lshell", "shell", "shell2"});
    model.study("std4").feature("freq").setSolveFor("/multiphysics/lssh1", false);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/lssh2", false);
    model.study("std4").feature("freq").set("disabledcoupling", new String[]{"lssh1", "lssh2"});
    model.study("std4").label("\u7814\u7a76\uff1a\u9891\u7387\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().duplicate("lshl4", "lshl2");
    model.result().dataset("lshl4").set("data", "dset4");
    model.result("pg2").run();
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayaxis", "y");
    model.result("pg2").set("relpadding", 1);
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("manualindexing", true);
    model.result("pg2").feature("surf3").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("manualindexing", true);
    model.result("pg2").feature().duplicate("surf4", "surf3");
    model.result("pg2").feature("surf4").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").set("data", "lshl4");
    model.result("pg2").feature("surf4").set("expr", "lshell2.mises_peak");
    model.result("pg2").feature("surf4").set("arrayindex", 1);
    model.result("pg2").run();
    model.result("pg2").feature("surf4").feature("def1").set("expr", new String[]{"u4", "v4", "w4"});
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("arrayaxis", "y");
    model.result("pg3").set("relpadding", 1);
    model.result("pg3").feature("lss1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lss2", "lss1");
    model.result("pg3").feature("lss2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("lss2").set("data", "dset4");
    model.result("pg3").feature("lss2").set("expr", "lshell2.disp");
    model.result("pg3").feature("lss2").set("titletype", "none");
    model.result("pg3").feature("lss2").set("inheritplot", "lss1");
    model.result("pg3").run();

    model.component("comp1").physics().copy("shell3", "shell");
    model.component("comp1").physics("shell3").label("\u58f3\uff08ESL \u7406\u8bba\uff09");
    model.component("comp1").physics("shell3").feature("llem1").set("allLayers", true);
    model.component("comp1").physics("shell3").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell3").feature("fl1").label("\u9762\u8f7d\u8377\uff0c\u9876\u90e8");
    model.component("comp1").physics("shell3").feature("fl1").selection().set(1);
    model.component("comp1").physics("shell3").feature("fl1").set("loadLocation", "top");
    model.component("comp1").physics("shell3").feature("fl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-p0*exp(-j*mn*atan2(Y,X))"});
    model.component("comp1").physics("shell3").feature().duplicate("fl2", "fl1");
    model.component("comp1").physics("shell3").feature("fl2").label("\u9762\u8f7d\u8377\uff0c\u5e95\u90e8");
    model.component("comp1").physics("shell3").feature("fl2").set("loadLocation", "bottom");

    model.study().create("std5");
    model.study("std5").create("eig", "Eigenfrequency");
    model.study("std5").feature("eig").set("plotgroup", "Default");
    model.study("std5").feature("eig").set("storefact", false);
    model.study("std5").feature("eig").set("solnum", "auto");
    model.study("std5").feature("eig").set("notsolnum", "auto");
    model.study("std5").feature("eig").set("outputmap", new String[]{});
    model.study("std5").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std5").feature("eig").set("ngenAUX", "1");
    model.study("std5").feature("eig").set("goalngenAUX", "1");
    model.study("std5").feature("eig").set("ngenAUX", "1");
    model.study("std5").feature("eig").set("goalngenAUX", "1");
    model.study("std5").feature("eig").setSolveFor("/physics/lshell", true);
    model.study("std5").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std5").feature("eig").setSolveFor("/physics/shell2", true);
    model.study("std5").feature("eig").setSolveFor("/physics/lshell2", true);
    model.study("std5").feature("eig").setSolveFor("/physics/shell3", true);
    model.study("std5").feature("eig").setSolveFor("/multiphysics/lssh1", true);
    model.study("std5").feature("eig").setSolveFor("/multiphysics/lssh2", true);
    model.study("std5").feature("eig").set("useadvanceddisable", true);
    model.study("std5").feature("eig").setSolveFor("/physics/lshell", false);
    model.study("std5").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std5").feature("eig").setSolveFor("/physics/shell2", false);
    model.study("std5").feature("eig").setSolveFor("/physics/lshell2", false);
    model.study("std5").feature("eig").set("disabledphysics", new String[]{"lshell", "shell", "shell2", "lshell2"});

    return model;
  }

  public static Model run2(Model model) {
    model.study("std5").feature("eig").setSolveFor("/multiphysics/lssh1", false);
    model.study("std5").feature("eig").setSolveFor("/multiphysics/lssh2", false);
    model.study("std5").feature("eig").set("disabledcoupling", new String[]{"lssh1", "lssh2"});
    model.study("std5").label("\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387\uff08ESL \u7406\u8bba\uff09");
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().dataset().duplicate("lshl5", "lshl1");
    model.result().dataset("lshl5").set("data", "dset5");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u632f\u578b\uff08ESL \u7406\u8bba\uff09");
    model.result("pg5").set("data", "none");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "shell3.disp");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("expr", new String[]{"u5", "v5", "w5"});
    model.result("pg5").run();
    model.result("pg5").set("data", "lshl5");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "shell3.disp");
    model.result("pg5").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().move("pg5", 2);

    model.study().create("std6");
    model.study("std6").create("freq", "Frequency");
    model.study("std6").feature("freq").setSolveFor("/physics/lshell", true);
    model.study("std6").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std6").feature("freq").setSolveFor("/physics/shell2", true);
    model.study("std6").feature("freq").setSolveFor("/physics/lshell2", true);
    model.study("std6").feature("freq").setSolveFor("/physics/shell3", true);
    model.study("std6").feature("freq").setSolveFor("/multiphysics/lssh1", true);
    model.study("std6").feature("freq").setSolveFor("/multiphysics/lssh2", true);
    model.study("std6").feature("freq").set("plist", 10);
    model.study("std6").feature("freq").set("useadvanceddisable", true);
    model.study("std6").feature("freq").setSolveFor("/physics/lshell", false);
    model.study("std6").feature("freq").setSolveFor("/physics/shell", false);
    model.study("std6").feature("freq").setSolveFor("/physics/shell2", false);
    model.study("std6").feature("freq").setSolveFor("/physics/lshell2", false);
    model.study("std6").feature("freq").set("disabledphysics", new String[]{"lshell", "shell", "shell2", "lshell2"});
    model.study("std6").feature("freq").setSolveFor("/multiphysics/lssh1", false);
    model.study("std6").feature("freq").setSolveFor("/multiphysics/lssh2", false);
    model.study("std6").feature("freq").set("disabledcoupling", new String[]{"lssh1", "lssh2"});
    model.study("std6").label("\u7814\u7a76\uff1a\u9891\u7387\uff08ESL \u7406\u8bba\uff09");
    model.study("std6").setGenPlots(false);
    model.study("std6").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().dataset().duplicate("lshl6", "lshl4");
    model.result().dataset("lshl6").set("data", "dset6");
    model.result("pg2").feature("surf4").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf5", "surf4");
    model.result("pg2").feature("surf5").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").set("data", "lshl6");
    model.result("pg2").feature("surf5").set("expr", "shell3.mises_peak");
    model.result("pg2").feature("surf5").set("arrayindex", 2);
    model.result("pg2").run();
    model.result("pg2").feature("surf5").feature("def1").set("expr", new String[]{"u5", "v5", "w5"});
    model.result("pg2").run();
    model.result("pg2").create("tlan1", "TableAnnotation");
    model.result("pg2").feature("tlan1").set("arraydim", "1");
    model.result("pg2").feature("tlan1").set("source", "localtable");
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", -0.8, 0, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0.5, 0, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u591a\u6a21\u578b\u65b9\u6cd5", 0, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", -0.8, 1, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 1.6, 1, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u5206\u5c42\u7406\u8bba", 1, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", -0.8, 2, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 2.7, 2, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "ESL \u7406\u8bba", 2, 3);
    model.result("pg2").feature("tlan1").set("showpoint", false);
    model.result("pg2").feature("tlan1").set("anchorpoint", "lowermiddle");
    model.result("pg2").run();

    model.view("view4").set("showgrid", false);

    model.result("pg3").feature("lss2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lss3", "lss2");
    model.result("pg3").feature("lss3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("lss3").set("data", "dset6");
    model.result("pg3").feature("lss3").set("expr", "shell3.disp");
    model.result("pg3").run();
    model.result("pg2").feature("tlan1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("tlan1", "pg2/tlan1");
    model.result("pg3").feature("tlan1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Mises \u5cf0\u503c\u5e94\u529b\uff0c\u5168\u539a\u5ea6");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("legendpos", "middleright");
    model.result("pg6").create("thr1", "ThroughThickness");
    model.result("pg6").feature("thr1").set("markerpos", "datapoints");
    model.result("pg6").feature("thr1").set("linewidth", "preference");
    model.result("pg6").feature("thr1").selection().set(2);
    model.result("pg6").feature("thr1").set("expr", "lshell.mises_peak");
    model.result("pg6").feature("thr1").set("unit", "GPa");
    model.result("pg6").feature("thr1").set("includeinterfaces", "exterior");
    model.result("pg6").feature("thr1").set("linestyle", "dashed");
    model.result("pg6").feature("thr1").set("linecolor", "blue");
    model.result("pg6").feature("thr1").set("linewidth", 2);
    model.result("pg6").feature("thr1").set("legend", true);
    model.result("pg6").feature("thr1").set("legendmethod", "manual");
    model.result("pg6").feature("thr1").setIndex("legends", "\u591a\u6a21\u578b\u65b9\u6cd5", 0);
    model.result("pg6").feature().duplicate("thr2", "thr1");
    model.result("pg6").run();
    model.result("pg6").feature("thr2").set("expr", "shell.mises_peak");
    model.result("pg6").feature("thr2").set("titletype", "none");
    model.result("pg6").feature("thr2").set("legend", false);
    model.result("pg6").feature().duplicate("thr3", "thr2");
    model.result("pg6").run();
    model.result("pg6").feature("thr3").set("expr", "shell2.mises_peak");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("thr4", "thr1");
    model.result("pg6").run();
    model.result("pg6").feature("thr4").set("data", "dset4");
    model.result("pg6").feature("thr4").set("expr", "lshell2.mises_peak");
    model.result("pg6").feature("thr4").set("titletype", "none");
    model.result("pg6").feature("thr4").set("linestyle", "solid");
    model.result("pg6").feature("thr4").set("linecolor", "green");
    model.result("pg6").feature("thr4").set("linewidth", 1);
    model.result("pg6").feature("thr4").setIndex("legends", "Layerwise Theory", 0);
    model.result("pg6").feature("thr4").setIndex("legends", "\u5206\u5c42\u7406\u8bba", 0);
    model.result("pg6").feature().duplicate("thr5", "thr4");
    model.result("pg6").run();
    model.result("pg6").feature("thr5").set("data", "dset6");
    model.result("pg6").feature("thr5").set("expr", "shell3.mises_peak");
    model.result("pg6").feature("thr5").set("linecolor", "red");
    model.result("pg6").feature("thr5").setIndex("legends", "ESL \u7406\u8bba", 0);
    model.result("pg6").run();
    model.result("pg6").create("tlan1", "TableAnnotation");
    model.result("pg6").feature("tlan1").set("source", "localtable");
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.2, 0, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.005, 0, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u78b3\u7ea4-\u73af\u6c27", 0, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.2, 1, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.013, 1, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 1, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.2, 2, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.028, 2, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "PVC \u6ce1\u6cab", 2, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.2, 3, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.045, 3, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 3, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.2, 4, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.053, 4, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u78b3\u7ea4-\u73af\u6c27", 4, 2);
    model.result("pg6").feature("tlan1").set("showpoint", false);
    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u6700\u5927 Mises \u5cf0\u503c\u5e94\u529b\u6bd4\u8f83");
    model.result().evaluationGroup("eg1").set("data", "lshl2");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg1").feature("max1").setIndex("expr", "lshell.mises_peak", 0);
    model.result().evaluationGroup("eg1").feature("max1").setIndex("unit", "GPa", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("max2", "max1");
    model.result().evaluationGroup("eg1").feature("max2").set("data", "lshl4");
    model.result().evaluationGroup("eg1").feature("max2").setIndex("expr", "lshell2.mises_peak", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("max3", "max2");
    model.result().evaluationGroup("eg1").feature("max3").set("data", "lshl6");
    model.result().evaluationGroup("eg1").feature("max3").setIndex("expr", "shell3.mises_peak", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u6700\u5927\u4f4d\u79fb\u6bd4\u8f83");
    model.result().evaluationGroup("eg2").set("data", "lshl2");
    model.result().evaluationGroup("eg2").set("includeparameters", false);
    model.result().evaluationGroup("eg2").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg2").feature("max1").setIndex("expr", "lshell.disp", 0);
    model.result().evaluationGroup("eg2").feature().duplicate("max2", "max1");
    model.result().evaluationGroup("eg2").feature("max2").set("data", "lshl4");
    model.result().evaluationGroup("eg2").feature("max2").setIndex("expr", "lshell2.disp", 0);
    model.result().evaluationGroup("eg2").feature().duplicate("max3", "max2");
    model.result().evaluationGroup("eg2").feature("max3").set("data", "lshl6");
    model.result().evaluationGroup("eg2").feature("max3").setIndex("expr", "shell3.disp", 0);
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("\u7279\u5f81\u9891\u7387\u6bd4\u8f83");
    model.result().evaluationGroup("eg3").set("includeparameters", false);
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "freq", 0);
    model.result().evaluationGroup("eg3").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg3").feature("gev2").set("data", "dset3");
    model.result().evaluationGroup("eg3").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg3").feature("gev3").set("data", "dset5");
    model.result().evaluationGroup("eg3").run();

    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").setSolveFor("/physics/lshell2", false);
    model.study("std1").feature("eig").setSolveFor("/physics/shell3", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"lshell2", "shell3"});
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").setSolveFor("/physics/lshell2", false);
    model.study("std2").feature("freq").setSolveFor("/physics/shell3", false);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"lshell2", "shell3"});
    model.study("std3").feature("eig").setSolveFor("/physics/shell3", false);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"lshell", "shell", "shell2", "shell3"});
    model.study("std4").feature("freq").setSolveFor("/physics/shell3", false);
    model.study("std4").feature("freq").set("disabledphysics", new String[]{"lshell", "shell", "shell2", "shell3"});

    model.result("pg3").run();

    model.title("\u4f7f\u7528\u591a\u6a21\u578b\u65b9\u6cd5\u5206\u6790\u590d\u5408\u6750\u6599\u53f6\u7247");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7531\u78b3-\u73af\u6c27\u6811\u8102\u3001\u73bb\u7483-\u4e59\u70ef\u57fa\u916f\u548c PVC \u6ce1\u6cab\u6750\u6599\u5236\u6210\u7684\u5939\u5c42\u590d\u5408\u6750\u6599\u53f6\u7247\uff0c\u5176\u4e2d\u91c7\u7528\u4e09\u79cd\u4e0d\u540c\u7684\u65b9\u6cd5\u5bf9\u5939\u5c42\u590d\u5408\u6750\u6599\u7ed3\u6784\u8fdb\u884c\u5efa\u6a21\uff1a\u5206\u5c42\u7406\u8bba\u3001\u7b49\u6548\u5355\u5c42 (ESL) \u7406\u8bba\u548c\u591a\u6a21\u578b\u65b9\u6cd5\u3002\u5bf9\u6307\u5b9a\u7684\u8f7d\u8377\u6267\u884c\u6a21\u6001\u548c\u9891\u7387\u54cd\u5e94\u5206\u6790\uff0c\u5e76\u5c06\u591a\u6a21\u578b\u65b9\u6cd5\u4e0e\u4f20\u7edf\u7684\u5206\u5c42\u548c ESL \u7406\u8bba\u5728\u6027\u80fd\u548c\u7ed3\u679c\uff08\u5305\u62ec\u5168\u539a\u5ea6\u5e94\u529b\u5206\u5e03\uff09\u51c6\u786e\u6027\u65b9\u9762\u8fdb\u884c\u6bd4\u8f83\u3002\n\n\u7ed3\u679c\u8868\u660e\uff0c\u5206\u5c42\u7406\u8bba\u5728\u51c6\u786e\u6027\u65b9\u9762\u8868\u73b0\u51fa\u8272\uff0c\u4f46\u8ba1\u7b97\u91cf\u76f8\u5bf9\u8f83\u5927\u3002\u76f8\u53cd\uff0cESL \u7406\u8bba\u7684\u8ba1\u7b97\u6210\u672c\u8f83\u4f4e\uff0c\u4f46\u65e0\u6cd5\u6355\u83b7\u51c6\u786e\u7684\u5168\u539a\u5ea6\u548c\u6574\u4f53\u5e94\u529b\u5206\u5e03\u3002\u5c31\u5939\u5c42\u590d\u5408\u6750\u6599\u7ed3\u6784\u5efa\u6a21\u7684\u7cbe\u5ea6\u548c\u6027\u80fd\u800c\u8a00\uff0c\u591a\u6a21\u578b\u65b9\u6cd5\u4f3c\u4e4e\u662f\u6700\u4f73\u9009\u62e9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("composite_blade_multiple_model_method.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
