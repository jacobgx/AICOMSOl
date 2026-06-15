/*
 * composite_wheel_rim.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:32 by COMSOL 6.3.0.290. */
public class composite_wheel_rim {

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("th", "0.4[mm]", "\u5c42\u539a\u5ea6");
    model.param().set("pInflation", "2[bar]", "\u5145\u6c14\u538b\u529b");
    model.param().set("tireLoad", "1120[kg]*g_const", "\u8f6e\u4e0a\u7684\u8f7d\u8377");
    model.param().set("phiLoad", "0", "\u5cf0\u503c\u8d1f\u8f7d\u89d2\u5ea6");
    model.param().set("RPM", "3000[rpm]", "\u8f66\u8f6e\u8f6c\u901f");
    model.param().set("omega", "2*pi[rad]*RPM", "\u8f66\u8f6e\u89d2\u901f\u5ea6");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "loadDistr");
    model.component("comp1").func("an1").set("expr", "(abs(atan2(x,y)-z*pi/180)<pi/6)*cos(3*(atan2(x,y)-z*pi/180))");
    model.component("comp1").func("an1").set("args", "x, y, z");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", 1, 2);
    model.component("comp1").func("an1").set("fununit", "Pa");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "composite_wheel_rim.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u8f6e\u8f8b");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("imp1", 11, 12, 13, 14, 15, 16, 17, 18, 19, 24, 26, 28, 34, 35, 36);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u8f6e\u6bc2\u548c\u8f90\u6761");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u8f6e\u80ce\u9644\u4ef6");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("imp1", 16, 17, 18, 19, 24, 26, 35, 36);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").label("\u56fa\u5b9a\u5230\u8f6e\u6bc2");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("imp1", 8, 30);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,72,288)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").label("\u8f90\u6761\u8f6e\u8f8b\u5355\u4f4d");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "inf");
    model.component("comp1").geom("geom1").feature("cylsel1").set("angle1", 18);
    model.component("comp1").geom("geom1").feature("cylsel1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_sel2");
    model.component("comp1").cpl("intop1").set("frame", "material");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic strength parameters, Voigt notation");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 58%]");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .label("Orthotropic strength parameters, Voigt notation");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"2060[MPa]", "78[MPa]", "78[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmats", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"1590[MPa]", "200[MPa]", "200[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmacs", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"157[MPa]", "157[MPa]", "157[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmass", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"138[GPa]", "8.7[GPa]"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.28", "0.45"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "5[GPa]");
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("rotation", 0, 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", 0, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", 0, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat1").setIndex("link", "mat1", 3);
    model.material("lmat1").setIndex("rotation", 0, 3);
    model.material("lmat1").setIndex("thickness", "th", 3);
    model.material("lmat1").setIndex("meshPoints", 1, 3);
    model.material("lmat1").setIndex("tag", "lmat1_4", 3);
    model.material("lmat1").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat1").setIndex("link", "mat1", 3);
    model.material("lmat1").setIndex("rotation", 0, 3);
    model.material("lmat1").setIndex("thickness", "th", 3);
    model.material("lmat1").setIndex("meshPoints", 1, 3);
    model.material("lmat1").setIndex("tag", "lmat1_4", 3);
    model.material("lmat1").setIndex("rotation", 45, 1);
    model.material("lmat1").setIndex("rotation", 90, 2);
    model.material("lmat1").setIndex("rotation", -45, 3);
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").selection().named("geom1_comsel1");
    model.component("comp1").material("llmat1").set("transform", "symmetry");
    model.component("comp1").material("llmat1").set("middlePlane", "top");
    model.component("comp1").material("llmat1").set("widthRatio", 0.6);
    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").selection().named("geom1_sel1");
    model.component("comp1").material("stlmat1").set("transform", "repeated");
    model.component("comp1").material("stlmat1").set("repeated", 2);
    model.component("comp1").material("stlmat1").set("middlePlane", "top");
    model.component("comp1").material("stlmat1").feature("stllmat1").set("transform", "symmetry");
    model.component("comp1").material("stlmat1").set("widthRatio", 0.6);

    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").selection().all();
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 2);
    model.component("comp1").physics("shell").feature("fix1").selection().named("geom1_sel3");
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().named("geom1_sel1");
    model.component("comp1").physics("shell").feature("fl1").set("loadLocation", "bottom");
    model.component("comp1").physics("shell").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("shell").feature("fl1").set("pressure", "-pInflation");
    model.component("comp1").physics("shell").feature().duplicate("fl2", "fl1");
    model.component("comp1").physics("shell").feature("fl2").selection().named("geom1_sel2");
    model.component("comp1").physics("shell").feature("fl2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("shell").feature("fl2").set("forceType", "ForceArea");
    model.component("comp1").physics("shell").feature("fl2")
         .set("forceReferenceArea", new String[]{"-loadAmpl*loadDistr(X,Y,phiLoad)", "0", "0.2*loadAmpl*loadDistr(X,Y,phiLoad)*(2*(Z>0)-1)"});
    model.component("comp1").physics("shell").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("shell").feature("ge1").setIndex("name", "loadAmpl", 0, 0);
    model.component("comp1").physics("shell").feature("ge1")
         .setIndex("equation", "loadAmpl*intop1(loadDistr(X,Y,0)*cos(atan2(X,Y)))-tireLoad", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").set("SourceTermQuantity", "force");
    model.component("comp1").physics("shell").create("rotf1", "RotatingFrame", 2);
    model.component("comp1").physics("shell").feature("rotf1").set("Ovm", "omega");
    model.component("comp1").physics("shell").feature("rotf1").set("RotationalDirection", "Clockwise");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.07);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.006);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.study("std1").label("\u7814\u7a76\uff1a\u7a33\u6001");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"shell/rotf1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shelllshl", "LayeredMaterial");
    model.result().dataset("dset1shelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shelllshl");
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
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", "2e8");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("lss1", "LayeredMaterialSlice");
    model.result("pg2").feature("lss1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg2").feature("lss1").set("threshold", "manual");
    model.result("pg2").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("lss1").set("colortable", "Prism");
    model.result("pg2").feature("lss1").set("colortabletrans", "none");
    model.result("pg2").feature("lss1").set("colorscalemode", "linear");
    model.result("pg2").feature("lss1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg2").feature("lss1").set("locdef", "relative");
    model.result("pg2").feature("lss1").set("localzrel", "shell.z");
    model.result("pg2").feature("lss1").create("def", "Deform");
    model.result("pg2").feature("lss1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (shell)");
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (shell)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("thr1", "ThroughThickness");
    model.result("pg3").feature("thr1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg3").feature("thr1").set("legend", true);
    model.result("pg3").feature("thr1").set("posentry", "selection");
    model.result("pg3").feature("thr1").selection().geom("geom1", 0);
    model.result("pg3").feature("thr1").selection().set(1);
    model.result("pg3").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (shell)");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (shell)");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").feature("lss1").set("rangecoloractive", true);
    model.result("pg2").feature("lss1").set("rangecolormax", "2e8");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("thr1").selection().set(74, 95);
    model.result("pg3").feature("thr1").set("thicknesscoordinateunit", "mm");
    model.result("pg3").feature("thr1").set("includeinterfaces", "all");
    model.result("pg3").feature("thr1").set("legendmethod", "manual");
    model.result("pg3").feature("thr1").setIndex("legends", "\u70b9=74\uff08\u8f6e\u8f8b\uff09", 0);
    model.result("pg3").feature("thr1").setIndex("legends", "\u70b9=95\uff08\u8f90\u6761\uff09", 1);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\uff0c\u5207\u9762\uff08\u8f6e\u6bc2\u548c\u8f90\u6761\uff09");
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().named("geom1_comsel1");
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").feature("lss1").set("locdef", "layermidplanes");
    model.result("pg4").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg4").feature("lss1").set("yseparation", "0.15*6");
    model.result("pg4").feature("lss1").set("showdescriptions", true);
    model.result("pg4").feature("lss1").set("descriptionseparation", "0.2*2");
    model.result("pg4").run();
    model.result("pg4").feature("lss1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("lss1").feature("def").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b\uff0c\u5207\u9762\uff08\u8f6e\u8f8b\uff09");
    model.result("pg5").selection().named("geom1_sel1");
    model.result("pg5").set("view", "new");
    model.result("pg5").run();
    model.result("pg5").feature("lss1").set("rangecolormax", "1e8");
    model.result("pg5").feature("lss1").set("xseparation", "0.15*4");
    model.result("pg5").feature("lss1").set("yseparation", "0.15*4");
    model.result("pg5").feature("lss1").set("showdescriptions", false);
    model.result("pg5").run();
    model.result("pg5").create("tlan1", "TableAnnotation");
    model.result("pg5").feature("tlan1").set("source", "localtable");
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.7, 0, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u5c42 1", 0, 3);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 2.8, 1, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u5c42 4", 1, 3);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.7, 2, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 2.4, 2, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u5c42 13", 2, 3);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 2.8, 3, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 2.4, 3, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 3, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u5c42 16", 3, 3);
    model.result("pg5").feature("tlan1").set("showpoint", false);
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u7279\u5f81\u9891\u7387");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"shell/fl1", "shell/fl2", "shell/ge1"});
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset2shelllshl", "LayeredMaterial");
    model.result().dataset("dset2shelllshl").set("data", "dset2");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2shelllshl");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").label("\u632f\u578b (shell)");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg6").feature("surf1").set("inheritplot", "none");
    model.result("pg6").set("data", "dset2shelllshl");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
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
    model.result("pg6").set("applyselectiontodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("looplevel", new int[]{3});
    model.result("pg6").run();
    model.result("pg6").set("looplevel", new int[]{4});
    model.result("pg6").run();
    model.result("pg6").set("looplevel", new int[]{6});
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (shell)");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").set("edgecolor", "cyan");
    model.result("pg7").create("lss1", "LayeredMaterialSlice");
    model.result("pg7").feature("lss1").set("expr", new String[]{"shell.zl_rel"});
    model.result("pg7").feature("lss1").set("threshold", "manual");
    model.result("pg7").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("lss1").set("colortable", "RainbowLight");
    model.result("pg7").feature("lss1").set("colortabletrans", "none");
    model.result("pg7").feature("lss1").set("colorscalemode", "linear");
    model.result("pg7").feature("lss1").label("\u9876\u90e8\u548c\u5e95\u90e8\uff1a\u591a\u5c42\u6750\u6599");
    model.result("pg7").feature("lss1").set("locdef", "relative");
    model.result("pg7").feature("lss1").set("localzrel", "-1 1");
    model.result("pg7").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (shell)");
    model.result("pg7").run();
    model.result("pg2").run();

    model.title("\u590d\u5408\u6750\u6599\u8f6e\u8f8b\u7684\u5e94\u529b\u548c\u6a21\u6001\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7531\u5c42\u538b\u590d\u5408\u6750\u6599\u5236\u6210\u7684\u8f6e\u8f8b\u3002\u7528\u4e8e\u5206\u6790\u7684\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u7531\u78b3-\u73af\u6c27\u6811\u8102\u6750\u6599\u5236\u6210\uff0c\u5305\u542b\u516b\u5c42\u5bf9\u79f0\u94fa\u5c42\u3002\n\n\u9996\u5148\uff0c\u5bf9\u590d\u5408\u6750\u6599\u8f6e\u8f8b\u53d7\u5145\u6c14\u538b\u529b\u548c\u8f6e\u80ce\u8f7d\u8377\u4f5c\u7528\u7684\u60c5\u51b5\u6267\u884c\u5e94\u529b\u5206\u6790\u3002\u5176\u6b21\uff0c\u5bf9\u8f6e\u8f8b\u53d7\u65cb\u8f6c\u6846\u67b6\u529b\u7684\u60c5\u51b5\u6267\u884c\u9884\u5e94\u529b\u7279\u5f81\u9891\u7387\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("composite_wheel_rim.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
