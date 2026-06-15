/*
 * wind_turbine_composite_blade.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:32 by COMSOL 6.3.0.290. */
public class wind_turbine_composite_blade {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Dynamics_and_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

    model.param().set("th", "0.28[mm]");
    model.param().descr("th", "\u5c42\u539a\u5ea6");
    model.param().set("thc", "15[cm]");
    model.param().descr("thc", "\u82af\u5c42\u539a\u5ea6");
    model.param().set("RPM", "15[rpm]");
    model.param().descr("RPM", "\u53f6\u7247\u6bcf\u5206\u949f\u8f6c\u6570");
    model.param().set("omega", "2*pi[rad]*RPM");
    model.param().descr("omega", "\u53f6\u7247\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "wind_turbine_composite_blade.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u76ae\u5c42\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(50);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7ffc\u6881\u8fb9\u754c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(3, 5);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u56fa\u5b9a\u8fb9");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(10);
    model.component("comp1").selection("sel3").set("groupcontang", true);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("sel3");
    model.component("comp1").cpl("aveop1").set("frame", "material");

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");
    model.component("comp1").coordSystem("sys1").create("rn1", "ReverseNormal");
    model.component("comp1").coordSystem("sys1").feature("rn1").selection().named("sel1");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 66, 69, 72, 74, 77, 80, 82, 85, 88);
    model.component("comp1").view("view1").set("showgrid", true);

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6750\u6599\uff1a\u78b3\u7ea4-\u73af\u6c27");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1aCE-[0]_10");
    model.material("lmat1").setIndex("thickness", "th*10", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u6750\u6599\uff1a\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f");
    model.material().create("lmat2", "LayeredMaterial", "");
    model.material("lmat2").label("\u591a\u5c42\u6750\u6599\uff1aGV-[0/45/-45/90]_s_5");
    model.material("lmat2").setIndex("link", "mat2", 0);
    model.material("lmat2").setIndex("thickness", "th*5", 0);
    model.material("lmat2").setIndex("meshPoints", 1, 0);
    model.material("lmat2").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat2").setIndex("link", "mat2", 1);
    model.material("lmat2").setIndex("rotation", "0.0", 1);
    model.material("lmat2").setIndex("thickness", "th*5", 1);
    model.material("lmat2").setIndex("meshPoints", 1, 1);
    model.material("lmat2").setIndex("tag", "lmat2_2", 1);
    model.material("lmat2").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat2").setIndex("link", "mat2", 1);
    model.material("lmat2").setIndex("rotation", "0.0", 1);
    model.material("lmat2").setIndex("thickness", "th*5", 1);
    model.material("lmat2").setIndex("meshPoints", 1, 1);
    model.material("lmat2").setIndex("tag", "lmat2_2", 1);
    model.material("lmat2").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat2").setIndex("link", "mat2", 2);
    model.material("lmat2").setIndex("rotation", "0.0", 2);
    model.material("lmat2").setIndex("thickness", "th*5", 2);
    model.material("lmat2").setIndex("meshPoints", 1, 2);
    model.material("lmat2").setIndex("tag", "lmat2_3", 2);
    model.material("lmat2").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat2").setIndex("link", "mat2", 2);
    model.material("lmat2").setIndex("rotation", "0.0", 2);
    model.material("lmat2").setIndex("thickness", "th*5", 2);
    model.material("lmat2").setIndex("meshPoints", 1, 2);
    model.material("lmat2").setIndex("tag", "lmat2_3", 2);
    model.material("lmat2").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat2").setIndex("link", "mat2", 3);
    model.material("lmat2").setIndex("rotation", "0.0", 3);
    model.material("lmat2").setIndex("thickness", "th*5", 3);
    model.material("lmat2").setIndex("meshPoints", 1, 3);
    model.material("lmat2").setIndex("tag", "lmat2_4", 3);
    model.material("lmat2").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat2").setIndex("link", "mat2", 3);
    model.material("lmat2").setIndex("rotation", "0.0", 3);
    model.material("lmat2").setIndex("thickness", "th*5", 3);
    model.material("lmat2").setIndex("meshPoints", 1, 3);
    model.material("lmat2").setIndex("tag", "lmat2_4", 3);
    model.material("lmat2").setIndex("layername", "\u5c42 5", 4);
    model.material("lmat2").setIndex("link", "mat2", 4);
    model.material("lmat2").setIndex("rotation", "0.0", 4);
    model.material("lmat2").setIndex("thickness", "th*5", 4);
    model.material("lmat2").setIndex("meshPoints", 1, 4);
    model.material("lmat2").setIndex("tag", "lmat2_5", 4);
    model.material("lmat2").setIndex("layername", "\u5c42 5", 4);
    model.material("lmat2").setIndex("link", "mat2", 4);
    model.material("lmat2").setIndex("rotation", "0.0", 4);
    model.material("lmat2").setIndex("thickness", "th*5", 4);
    model.material("lmat2").setIndex("meshPoints", 1, 4);
    model.material("lmat2").setIndex("tag", "lmat2_5", 4);
    model.material("lmat2").setIndex("layername", "\u5c42 6", 5);
    model.material("lmat2").setIndex("link", "mat2", 5);
    model.material("lmat2").setIndex("rotation", "0.0", 5);
    model.material("lmat2").setIndex("thickness", "th*5", 5);
    model.material("lmat2").setIndex("meshPoints", 1, 5);
    model.material("lmat2").setIndex("tag", "lmat2_6", 5);
    model.material("lmat2").setIndex("layername", "\u5c42 6", 5);
    model.material("lmat2").setIndex("link", "mat2", 5);
    model.material("lmat2").setIndex("rotation", "0.0", 5);
    model.material("lmat2").setIndex("thickness", "th*5", 5);
    model.material("lmat2").setIndex("meshPoints", 1, 5);
    model.material("lmat2").setIndex("tag", "lmat2_6", 5);
    model.material("lmat2").setIndex("layername", "\u5c42 7", 6);
    model.material("lmat2").setIndex("link", "mat2", 6);
    model.material("lmat2").setIndex("rotation", "0.0", 6);
    model.material("lmat2").setIndex("thickness", "th*5", 6);
    model.material("lmat2").setIndex("meshPoints", 1, 6);
    model.material("lmat2").setIndex("tag", "lmat2_7", 6);
    model.material("lmat2").setIndex("layername", "\u5c42 7", 6);
    model.material("lmat2").setIndex("link", "mat2", 6);
    model.material("lmat2").setIndex("rotation", "0.0", 6);
    model.material("lmat2").setIndex("thickness", "th*5", 6);
    model.material("lmat2").setIndex("meshPoints", 1, 6);
    model.material("lmat2").setIndex("tag", "lmat2_7", 6);
    model.material("lmat2").setIndex("layername", "\u5c42 8", 7);
    model.material("lmat2").setIndex("link", "mat2", 7);
    model.material("lmat2").setIndex("rotation", "0.0", 7);
    model.material("lmat2").setIndex("thickness", "th*5", 7);
    model.material("lmat2").setIndex("meshPoints", 1, 7);
    model.material("lmat2").setIndex("tag", "lmat2_8", 7);
    model.material("lmat2").setIndex("layername", "\u5c42 8", 7);
    model.material("lmat2").setIndex("link", "mat2", 7);
    model.material("lmat2").setIndex("rotation", "0.0", 7);
    model.material("lmat2").setIndex("thickness", "th*5", 7);
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
    model.material("lmat3").setIndex("meshPoints", 1, 0);
    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").feature("stllmat1")
         .label("\u591a\u5c42\u6750\u6599\u94fe\u63a5\uff1a\u78b3\u7ea4-\u73af\u6c27");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat2", "stllmat1");
    model.component("comp1").material("stlmat1").feature("stllmat2")
         .label("\u591a\u5c42\u6750\u6599\u94fe\u63a5\uff1a\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f");
    model.component("comp1").material("stlmat1").feature("stllmat2").set("link", "lmat2");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat3", "stllmat2");
    model.component("comp1").material("stlmat1").feature("stllmat3")
         .label("\u591a\u5c42\u6750\u6599\u94fe\u63a5\uff1aPVC \u6ce1\u6cab");
    model.component("comp1").material("stlmat1").feature("stllmat3").set("link", "lmat3");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat4", "stllmat2");
    model.component("comp1").material("stlmat1").feature().duplicate("stllmat5", "stllmat1");
    model.component("comp1").material("stlmat1").set("widthRatio", 0.4);
    model.component("comp1").material("stlmat1").set("showLabels", false);

    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").selection().all();
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").set("TransverseIsotropic", true);

    model.material("mat1").propertyGroup("def").set("density", new String[]{"1560"});
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"139e9", "9e9"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.32", "0.32"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"5.5e9"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"1890"});
    model.material("mat2").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"41e9", "9e9"});
    model.material("mat2").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.3", "0.3"});
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"4.1e9"});
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat3").propertyGroup("def").set("density", new String[]{"200"});
    model.material("mat3").propertyGroup("Enu").set("E", new String[]{"250e6"});
    model.material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.35"});

    model.component("comp1").physics("shell").feature("llem1").set("ShearCorrectionFactor", "UserDefined");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().named("sel3");
    model.component("comp1").physics("shell").create("gacc1", "GravityAcceleration", -1);

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("shell").feature("gacc1").set("loadGroup", "lg1");

    model.group("lg1").label("\u8f7d\u8377\u7ec4\uff1a\u91cd\u529b");
    model.group("lg1").paramName("lgG");

    model.component("comp1").physics("shell").create("rotf1", "RotatingFrame", 2);
    model.component("comp1").physics("shell").feature("rotf1").set("AxisOfRotation", "userdef");
    model.component("comp1").physics("shell").feature("rotf1")
         .set("rotaxbp", new String[]{"aveop1(X)", "aveop1(Y)", "aveop1(Z)"});
    model.component("comp1").physics("shell").feature("rotf1").set("rotaxdir", new int[]{0, 1, 0});
    model.component("comp1").physics("shell").feature("rotf1").set("RotationalDirection", "Clockwise");
    model.component("comp1").physics("shell").feature("rotf1").set("Ovm", "omega");
    model.component("comp1").physics("shell").feature("rotf1").set("SpinSoftening", false);

    model.group().create("lg2", "LoadGroup");

    model.component("comp1").physics("shell").feature("rotf1").set("loadGroup", "lg2");

    model.group("lg2").label("\u8f7d\u8377\u7ec4\uff1a\u79bb\u5fc3\u529b");
    model.group("lg2").paramName("lgCF");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("hidestatus", "ignore");
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.study("std1").label("\u7814\u7a76\uff1a\u7a33\u6001");
    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5\uff1a\u91cd\u529b", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5\uff1a\u79bb\u5fc3\u529b", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").feature("stat")
         .setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5\uff1a\u91cd\u529b+\u79bb\u5fc3\u529b", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shelllshl", "LayeredMaterial");
    model.result().dataset("dset1shelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shelllshl");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("\u5e94\u529b (shell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").feature("surf1").set("inheritplot", "none");
    model.result("pg1").set("data", "dset1shelllshl");
    model.result("pg1").run();
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().named("sel2");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u5c16\u90e8\u4f4d\u79fb");
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(110);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell.disp", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "lshl1");
    model.result().evaluationGroup("eg2").label("\u6700\u5927\u5e94\u529b");
    model.result().evaluationGroup("eg2").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg2").feature("max1").setIndex("expr", "shell.mises", 0);
    model.result().evaluationGroup("eg2").feature("max1").setIndex("unit", "MPa", 0);
    model.result().evaluationGroup("eg2").feature("max1").setIndex("descr", "von Mises stress", 0);
    model.result().evaluationGroup("eg2").feature("max1").setIndex("descr", "von Mises \u5e94\u529b", 0);
    model.result().evaluationGroup("eg2").run();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff1a\u76ae\u5c42+\u7ffc\u6881");
    model.result("pg1").set("view", "new");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", "1e8");

    model.view("view3").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff1a\u7ffc\u6881");
    model.result("pg2").set("data", "lshl1");
    model.result("pg2").set("view", "auto");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg3").feature("lss1").set("threshold", "manual");
    model.result("pg3").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("lss1").set("colortable", "Prism");
    model.result("pg3").feature("lss1").set("colortabletrans", "none");
    model.result("pg3").feature("lss1").set("colorscalemode", "linear");
    model.result("pg3").feature("lss1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg3").feature("lss1").set("locdef", "relative");
    model.result("pg3").feature("lss1").set("localzrel", "shell.z");
    model.result("pg3").feature("lss1").create("def", "Deform");
    model.result("pg3").feature("lss1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (shell)");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (shell)");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762\uff08\u78b3\u7ea4-\u73af\u6c27\uff09");
    model.result("pg3").run();
    model.result("pg3").feature("lss1").set("localzrel", 1);
    model.result("pg3").feature("lss1").set("rangecoloractive", true);
    model.result("pg3").feature("lss1").set("rangecolormax", "1e8");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\uff0c\u5207\u9762");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").feature("lss1").set("locdef", "physical");
    model.result("pg4").feature("lss1")
         .set("localzphys", "5*th, 17*th, 30*th, 50*th+0.5*thc, 70*th+thc, 83*th+thc, 95*th+thc");
    model.result("pg4").feature("lss1").set("slicedisplacement", "linear");
    model.result("pg4").feature("lss1").set("zseparation", 0.3);
    model.result("pg4").feature("lss1").set("showdescriptions", true);
    model.result("pg4").feature("lss1").set("descriptionseparation", 0.35);
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u78b3\u7ea4-\u73af\u6c27", 0, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 18, 1, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 1, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 2, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 2, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 37.5, 2, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 2, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 3, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 3, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 56, 3, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "PVC \u6ce1\u6cab", 3, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 4, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 4, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 76, 4, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 4, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 5, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 5, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 94, 5, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 5, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 80, 6, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 6, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 112, 6, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u78b3\u7ea4-\u73af\u6c27", 6, 3);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").feature("tlan1").set("orientation", "vertical");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").create("thr1", "ThroughThickness");
    model.result("pg5").feature("thr1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg5").feature("thr1").set("legend", true);
    model.result("pg5").feature("thr1").set("posentry", "selection");
    model.result("pg5").feature("thr1").selection().geom("geom1", 0);
    model.result("pg5").feature("thr1").selection().set(1);
    model.result("pg5").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (shell)");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (shell)");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "all", 0);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "von Mises \u5e94\u529b (MPa)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u539a\u5ea6\u5750\u6807 (mm)");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0);
    model.result("pg5").set("xmax", 100);
    model.result("pg5").set("ymin", 0);
    model.result("pg5").set("ymax", "60*1000*th");
    model.result("pg5").set("legendpos", "middleright");
    model.result("pg5").run();
    model.result("pg5").feature("thr1").selection().set(26);
    model.result("pg5").feature("thr1").set("unit", "MPa");
    model.result("pg5").feature("thr1").set("thicknesscoordinateunit", "mm");
    model.result("pg5").feature("thr1").set("includeinterfaces", "exterior");
    model.result("pg5").feature("thr1").set("linestyle", "cycle");
    model.result("pg5").feature("thr1").set("legendmethod", "manual");
    model.result("pg5").feature("thr1").setIndex("legends", "\u91cd\u529b", 0);
    model.result("pg5").feature("thr1").setIndex("legends", "\u79bb\u5fc3\u529b", 1);
    model.result("pg5").feature("thr1").setIndex("legends", "\u91cd\u529b+\u79bb\u5fc3\u529b", 2);
    model.result("pg5").run();
    model.result("pg5").create("tlan1", "TableAnnotation");
    model.result("pg5").feature("tlan1").set("source", "localtable");
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 30, 0, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "5*1000*th", 0, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u78b3\u7ea4-\u73af\u6c27", 0, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 30, 1, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "30*1000*th", 1, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u73bb\u7ea4-\u4e59\u70ef\u57fa\u916f", 1, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 30, 2, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "55*1000*th", 2, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "PVC \u6ce1\u6cab", 2, 2);
    model.result("pg5").feature("tlan1").set("showpoint", false);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (shell)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").set("edgecolor", "cyan");
    model.result("pg6").create("lss1", "LayeredMaterialSlice");
    model.result("pg6").feature("lss1").set("expr", new String[]{"shell.zl_rel"});
    model.result("pg6").feature("lss1").set("threshold", "manual");
    model.result("pg6").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("lss1").set("colortable", "RainbowLight");
    model.result("pg6").feature("lss1").set("colortabletrans", "none");
    model.result("pg6").feature("lss1").set("colorscalemode", "linear");
    model.result("pg6").feature("lss1").label("\u9876\u90e8\u548c\u5e95\u90e8\uff1a\u591a\u5c42\u6750\u6599");
    model.result("pg6").feature("lss1").set("locdef", "relative");
    model.result("pg6").feature("lss1").set("localzrel", "-1 1");
    model.result("pg6").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (shell)");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"shell.d"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").label("\u539a\u5ea6");
    model.result("pg7").create("syss", "CoordSysSurface");
    model.result("pg7").feature("syss").set("sys", "shellsys");
    model.result("pg7").feature("syss").label("\u58f3\u5c40\u90e8\u5750\u6807\u7cfb");
    model.result("pg7").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("view", "view3");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/shell", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").label("\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "th", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "th", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "RPM", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0,5,30)[rpm]", 0);
    model.study("std2").feature("stat").set("useloadcase", true);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5\uff1a\u79bb\u5fc3\u529b", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 0, 1);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 8);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std2");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().dataset().create("dset4shelllshl", "LayeredMaterial");
    model.result().dataset("dset4shelllshl").set("data", "dset4");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset4shelllshl");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").setIndex("looplevel", 7, 1);
    model.result("pg8").label("\u632f\u578b (shell)");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg8").feature("surf1").create("def", "Deform");
    model.result("pg8").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg8").feature("surf1").set("inheritplot", "none");
    model.result("pg8").set("data", "dset4shelllshl");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset4");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset4");
    model.result().evaluationGroup("std2mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{5, 1});

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{5, 3});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{5, 5});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{5, 7});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{1, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{2, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{3, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{4, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{5, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{6, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{7, 1});
    model.result("pg8").run();
    model.result("pg8").set("looplevel", new int[]{8, 1});
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u574e\u8d1d\u5c14\u56fe");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u9891\u7387 (Hz)");
    model.result("pg9").set("showlegends", false);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "freq", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "1/s", 0);
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg9").feature("glob1").set("linestyle", "cycle");
    model.result("pg9").feature("glob1").set("linemarker", "cycle");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg7");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("showframe", 25);
    model.result().export("anim1").run();
    model.result("pg1").run();

    model
         .title("\u98ce\u529b\u53d1\u7535\u673a\u590d\u5408\u6750\u6599\u53f6\u7247\u7684\u5e94\u529b\u548c\u6a21\u6001\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7531\u5c42\u538b\u590d\u5408\u6750\u6599\u5236\u6210\u7684\u98ce\u529b\u53d1\u7535\u673a\u53f6\u7247\u3002\u8fd9\u79cd\u538b\u590d\u5408\u6750\u6599\u5177\u6709\u78b3-\u73af\u6c27\u6811\u8102\u3001\u73bb\u7483-\u4e59\u70ef\u57fa\u916f\u4ee5\u53ca\u4ee5\u6ce1\u6cab\u4e3a\u82af\u6750\u7684 PVC \u6ce1\u6cab\u6750\u6599\u6784\u6210\u7684\u591a\u5c42\u7ed3\u6784\uff0c\u56e0\u6b64\u901a\u8fc7\u7b49\u6548\u5355\u5c42 (ESL) \u7406\u8bba\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u9996\u5148\uff0c\u5bf9\u590d\u5408\u6750\u6599\u53f6\u7247\u53d7\u5230\u91cd\u529b\u548c/\u6216\u79bb\u5fc3\u529b\u4f5c\u7528\u7684\u60c5\u51b5\u6267\u884c\u5e94\u529b\u5206\u6790\uff0c\u8ba1\u7b97\u53f6\u7247\u4e0a\u67d0\u4e2a\u7279\u5b9a\u70b9\u5728\u5404\u79cd\u8f7d\u8377\u5de5\u51b5\u4e0b\u7684\u5c16\u7aef\u4f4d\u79fb\u3001\u6700\u5927\u5e94\u529b\u503c\u4ee5\u53ca\u5168\u539a\u5ea6\u5e94\u529b\u5206\u5e03\u3002\u7136\u540e\uff0c\u5bf9\u53f6\u7247\u5728\u4e00\u5b9a\u8303\u56f4\u7684\u8fd0\u884c\u901f\u5ea6\u6267\u884c\u9884\u5e94\u529b\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u5e76\u751f\u6210\u574e\u8d1d\u5c14\u56fe\u6765\u63cf\u8ff0\u7279\u5f81\u9891\u7387\u968f\u65cb\u8f6c\u901f\u5ea6\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();

    model.label("wind_turbine_composite_blade.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
