/*
 * composite_cylinder_buckling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:31 by COMSOL 6.3.0.290. */
public class composite_cylinder_buckling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Buckling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").create("buckling", "LinearBuckling");
    model.study("std1").feature("buckling").set("neigsactive", true);
    model.study("std1").feature("buckling").set("solnum", "auto");
    model.study("std1").feature("buckling").set("notsolnum", "auto");
    model.study("std1").feature("buckling").set("outputmap", new String[]{});
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").set("ngenAUX", "1");
    model.study("std1").feature("buckling").set("goalngenAUX", "1");
    model.study("std1").feature("buckling").setSolveFor("/physics/shell", true);

    model.param().set("r", "0.15[m]");
    model.param().descr("r", "\u6c14\u74f6\u534a\u5f84");
    model.param().set("l", "0.4[m]");
    model.param().descr("l", "\u6c14\u74f6\u957f\u5ea6");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Fc", "shell.LFcrit*1[N]");
    model.component("comp1").variable("var1").descr("Fc", "\u4e34\u754c\u5c48\u66f2\u8f7d\u8377");
    model.component("comp1").variable("var1").set("un", "u*nX+v*nY+w*nZ");
    model.component("comp1").variable("var1").descr("un", "\u6cd5\u5411\u4f4d\u79fb");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "l");
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").geom("geom1").run();

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1").label("Material: Carbon-Epoxy");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1700");
    model.material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"134e9", "9.2e9"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.28", "0.28"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "4.8e9");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1")
         .set("layername", new String[]{"\u5c42 1", "\u5c42 2", "\u5c42 3", "\u5c42 4", "\u5c42 5", "\u5c42 6", "\u5c42 7", "\u5c42 8"});
    model.material("lmat1")
         .set("link", new String[]{"mat1", "mat1", "mat1", "mat1", "mat1", "mat1", "mat1", "mat1"});
    model.material("lmat1").set("rotation", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.material("lmat1")
         .set("thickness", new String[]{"1e-4[m]", "1e-4[m]", "1e-4[m]", "1e-4[m]", "1e-4[m]", "1e-4[m]", "1e-4[m]", "1e-4[m]"});
    model.material("lmat1").set("meshPoints", new String[]{"2", "2", "2", "2", "2", "2", "2", "2"});
    model.material("lmat1")
         .set("tag", new String[]{"lmat1_1", "lmat1_2", "lmat1_3", "lmat1_4", "lmat1_5", "lmat1_6", "lmat1_7", "lmat1_8"});
    model.material("lmat1").set("matLink_int", new String[]{"", "", "", "", "", "", "", "", ""});
    model.material("lmat1")
         .set("intname", new String[]{"\u5c42 1 \u5411\u4e0b", "\u5c42 1-\u5c42 2", "\u5c42 2-\u5c42 3", "\u5c42 3-\u5c42 4", "\u5c42 4-\u5c42 5", "\u5c42 5-\u5c42 6", "\u5c42 6-\u5c42 7", "\u5c42 7-\u5c42 8", "\u5c42 8 \u5411\u4e0a"});
    model.material("lmat1")
         .set("position", new String[]{"0", "1E-4", "2E-4", "3E-4", "4E-4", "5E-4", "6E-4", "7E-4", "8E-4"});
    model.material("lmat1")
         .set("labelModified", new String[]{"false", "false", "false", "false", "false", "false", "false", "false", "false"});
    model.material("lmat1").label("Layered Material: [0/0/45/-45]_s");
    model.material("lmat1")
         .set("layername", new String[]{"Layer 1", "Layer 2", "Layer 3", "Layer 4", "Layer 5", "Layer 6", "Layer 7", "Layer 8"});
    model.material("lmat1").set("link", new String[]{"", "", "", "", "", "", "", ""});
    model.material("lmat1").set("rotation", new int[]{0, 0, 45, -45, -45, 45, 0, 0});
    model.material("lmat1")
         .set("thickness", new String[]{"0.125[mm]", "0.125[mm]", "0.125[mm]", "0.125[mm]", "0.125[mm]", "0.125[mm]", "0.125[mm]", "0.125[mm]"});
    model.material("lmat1").set("meshPoints", new int[]{1, 1, 1, 1, 1, 1, 1, 1});
    model.material("lmat1")
         .set("intname", new String[]{"Layer 1 down", "Layer 1-Layer 2", "Layer 2-Layer 3", "Layer 3-Layer 4", "Layer 4-Layer 5", "Layer 5-Layer 6", "Layer 6-Layer 7", "Layer 7-Layer 8", "Layer 8 up"});
    model.material("lmat1")
         .set("position", new String[]{"0", "0.125[mm]", "2*0.125[mm]", "3*0.125[mm]", "4*0.125[mm]", "5*0.125[mm]", "6*0.125[mm]", "7*0.125[mm]", "8*0.125[mm]"});
    model.material("lmat1").set("widthRatio", 0.6);
    model.material("lmat1")
         .set("labelModified", new String[]{"false", "false", "false", "false", "false", "false", "false", "false", "false"});
    model.material("lmat1").propertyGroup("def").label("Basic");
    model.material("lmat1")
         .set("link", new String[]{"mat1", "mat1", "mat1", "mat1", "mat1", "mat1", "mat1", "mat1"});
    model.material("lmat1").set("matLink_int", new String[]{"", "", "", "", "", "", "", "", ""});
    model.material().create("sw1", "Switch", "");
    model.material("sw1").feature().copy("lmat1", "lmat1", "");
    model.material().remove("lmat1");
    model.material("sw1").feature().duplicate("lmat2", "lmat1");
    model.material("sw1").feature("lmat2").label("\u591a\u5c42\u6750\u6599\uff1a[90/90/45/-45]_s");
    model.material("sw1").feature("lmat2").setIndex("rotation", 90, 0);
    model.material("sw1").feature("lmat2").setIndex("rotation", 90, 1);
    model.material("sw1").feature("lmat2").setIndex("rotation", 90, 6);
    model.material("sw1").feature("lmat2").setIndex("rotation", 90, 7);
    model.material("sw1").feature().duplicate("lmat3", "lmat2");
    model.material("sw1").feature("lmat3").label("\u591a\u5c42\u6750\u6599\uff1a[90/0/90/0]_s");
    model.material("sw1").feature("lmat3").setIndex("rotation", 0, 1);
    model.material("sw1").feature("lmat3").setIndex("rotation", 90, 2);
    model.material("sw1").feature("lmat3").setIndex("rotation", 0, 3);
    model.material("sw1").feature("lmat3").setIndex("rotation", 0, 4);
    model.material("sw1").feature("lmat3").setIndex("rotation", 90, 5);
    model.material("sw1").feature("lmat3").setIndex("rotation", 0, 6);
    model.material("sw1").feature().duplicate("lmat4", "lmat3");
    model.material("sw1").feature("lmat4").label("\u591a\u5c42\u6750\u6599\uff1a[45/45/45/45]_as");
    model.material("sw1").feature("lmat4").setIndex("rotation", 45, 0);
    model.material("sw1").feature("lmat4").setIndex("rotation", 45, 1);
    model.material("sw1").feature("lmat4").setIndex("rotation", 45, 2);
    model.material("sw1").feature("lmat4").setIndex("rotation", 45, 3);
    model.material("sw1").feature("lmat4").setIndex("rotation", -45, 4);
    model.material("sw1").feature("lmat4").setIndex("rotation", -45, 5);
    model.material("sw1").feature("lmat4").setIndex("rotation", -45, 6);
    model.material("sw1").feature("lmat4").setIndex("rotation", -45, 7);
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").selection().all();
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").set("TransverseIsotropic", true);
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(2, 3, 7, 10);
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp1").selection().set(4, 5, 8, 11);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp1").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("shell").feature("el1").selection().set(4, 5, 8, 11);
    model.component("comp1").physics("shell").feature("el1").set("forceType", "TotalForce");
    model.component("comp1").physics("shell").feature("el1").set("force", new String[]{"0", "0", "-1[N]"});

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,4)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,4)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol3");
    model.batch("pm1").run("compute");

    model.result().dataset("dset3").set("frametype", "spatial");
    model.result().dataset().create("dset3shelllshl", "LayeredMaterial");
    model.result().dataset("dset3shelllshl").set("data", "dset3");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset3shelllshl");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u632f\u578b (shell)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").feature("surf1").set("inheritplot", "none");
    model.result("pg1").set("data", "dset3shelllshl");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("scenelight", false);
    model.component("comp1").view("view1").camera().set("zoomanglefull", 14.451568603515625);
    model.component("comp1").view("view1").set("locked", true);

    model.result("pg1").label("\u632f\u578b\uff1a[0/0/45/-45]_s");
    model.result("pg1").set("looplevel", new int[]{1, 1});
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("unitintitle", false);
    model.result("pg1").set("descriptionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "un");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"shell.d"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").label("\u539a\u5ea6");
    model.result("pg2").create("syss", "CoordSysSurface");
    model.result("pg2").feature("syss").set("sys", "shellsys");
    model.result("pg2").feature("syss").label("\u58f3\u5c40\u90e8\u5750\u6807\u7cfb");
    model.result("pg2").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff1a[45/45/45/45]_as");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("expr", "shell.mises");
    model.result("pg3").feature("lss1").set("colortable", "RainbowLight");
    model.result("pg3").feature("lss1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("lss1").feature("def1").set("expr", new String[]{"shell.u", "shell.v", "w"});
    model.result("pg3").feature("lss1").feature("def1").setIndex("expr", "shell.w", 2);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("\u632f\u578b\uff1a[90/90/45/-45]_s");
    model.result("pg4").set("looplevel", new int[]{1, 2});
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u632f\u578b\uff1a[90/0/90/0]_s");
    model.result("pg5").set("looplevel", new int[]{1, 3});
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u632f\u578b\uff1a[45/45/45/45]_as");
    model.result("pg6").set("looplevel", new int[]{1, 4});
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u632f\u578b\uff1a\u6bd4\u8f83");
    model.result("pg7").set("solutionintitle", false);
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").set("descriptionintitle", true);
    model.result("pg7").set("unitintitle", true);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("data", "dset3shelllshl");
    model.result("pg7").feature("surf1").set("looplevel", new int[]{1, 1});
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("def").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("looplevel", new int[]{1, 2});
    model.result("pg7").feature("surf2").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("def").setIndex("expr", "v+1.3*l", 1);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf3", "surf2");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").set("looplevel", new int[]{1, 3});
    model.result("pg7").run();
    model.result("pg7").feature("surf3").feature("def").setIndex("expr", "v", 1);
    model.result("pg7").feature("surf3").feature("def").set("expr", new String[]{"shell.u", "v", "w+1.3*l"});
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf4", "surf3");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").set("looplevel", new int[]{1, 4});
    model.result("pg7").run();
    model.result("pg7").feature("surf4").feature("def").setIndex("expr", "v+1.3*l", 1);
    model.result("pg7").run();
    model.result("pg7").create("tlan1", "TableAnnotation");
    model.result("pg7").feature("tlan1").set("source", "localtable");
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-0.15*l", 0, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "[0/0/45/-45]_s", 0, 3);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 1, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "1.3*l", 1, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-0.15*l", 1, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "[90/90/45/-45]_s", 1, 3);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 2, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 2, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-0.15*l+1.3*l", 2, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "[90/0/90/0]_s", 2, 3);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "1.3*l", 3, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-0.15*l+1.3*l", 3, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "[45/45/45/45]_as", 3, 3);
    model.result("pg7").feature("tlan1").set("showpoint", false);
    model.result("pg7").feature("tlan1").set("anchorpoint", "lowermiddle");
    model.result("pg7").run();
    model.result("pg7").set("view", "new");
    model.result("pg7").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u4e34\u754c\u5c48\u66f2\u8f7d\u8377");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "Fc", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "kN", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg4").run();
    model.result().move("pg4", 2);
    model.result().move("pg5", 3);
    model.result().move("pg6", 4);
    model.result().move("pg4", 1);
    model.result().move("pg5", 2);
    model.result().move("pg6", 3);
    model.result("pg7").run();

    model.title("\u590d\u5408\u6750\u6599\u6c14\u74f6\u7684\u5c48\u66f2\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u4e00\u4e2a\u53d7\u538b\u7f29\u8f7d\u8377\u548c\u56fa\u5b9a\u7aef\u6761\u4ef6\u7ea6\u675f\u7684\u590d\u5408\u6750\u6599\u6c14\u74f6\u6267\u884c\u5c48\u66f2\u5206\u6790\uff0c\u5e76\u8ba1\u7b97\u4e34\u754c\u8f7d\u8377\u56e0\u5b50\u3002\u590d\u5408\u6750\u6599\u6c14\u74f6\u7531\u516b\u5c42\u5177\u6709\u4e0d\u540c\u7ea4\u7ef4\u65b9\u5411\u7684\u78b3\u7ea4\u7ef4\u589e\u5f3a\u805a\u5408\u7269 (CFRP) \u5236\u6210\u3002\u672c\u4f8b\u91c7\u7528\u57fa\u4e8e\u7b49\u6548\u5355\u5c42 (ESL) \u7406\u8bba\u7684\u65b9\u6cd5\u3002\n\n\u9488\u5bf9\u4e0d\u540c\u7c7b\u578b\u7684\u5e73\u8861\u5c42\u5408\u677f\uff08\u4f8b\u5982\u5bf9\u79f0\u89d2\u94fa\u8bbe\u5c42\u5408\u677f\u548c\u53cd\u5bf9\u79f0\u89d2\u94fa\u8bbe\u5c42\u5408\u677f\uff09\uff0c\u5206\u6790\u4e86\u94fa\u5c42\u987a\u5e8f\u5bf9\u5c48\u66f2\u6a21\u6001\u5f62\u72b6\u548c\u4e34\u754c\u8f7d\u8377\u56e0\u5b50\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("composite_cylinder_buckling.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
