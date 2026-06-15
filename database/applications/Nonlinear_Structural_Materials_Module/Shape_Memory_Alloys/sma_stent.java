/*
 * sma_stent.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:37 by COMSOL 6.3.0.290. */
public class sma_stent {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Shape_Memory_Alloys");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").insertFile("sma_stent_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.param().set("para", "0");
    model.param().descr("para", "\u626b\u63cf\u53c2\u6570");
    model.param().set("pload", "0[N/m^2]");
    model.param().descr("pload", "\u5916\u52a0\u538b\u529b");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "ro");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", "Ro", 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", "1.4[mm]", 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", "1.4[mm]", 2, 1);
    model.component("comp1").func("int1").setIndex("fununit", "m", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("funcname", "temperature");
    model.component("comp1").func("int2").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int2").setIndex("table", -27, 0, 1);
    model.component("comp1").func("int2").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int2").setIndex("table", -27, 1, 1);
    model.component("comp1").func("int2").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int2").setIndex("table", 37, 2, 1);
    model.component("comp1").func("int2").setIndex("fununit", "degC", 0);
    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 2, 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", -1, 1, 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5e38\u89c4");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u5965\u6c0f\u4f53");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u9a6c\u6c0f\u4f53");

    model.component("comp1").physics("solid").create("sma1", "ShapeMemoryAlloy", 3);
    model.component("comp1").physics("solid").feature("sma1").selection().all();
    model.component("comp1").physics("solid").feature("sma1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("sma1").set("minput_temperature", "temperature(para)");
    model.component("comp1").physics("solid").feature("sma1").set("AusteniteMaterial", "mat2");
    model.component("comp1").physics("solid").feature("sma1").set("MartensiteMaterial", "mat3");
    model.component("comp1").physics("solid").feature("sma1").set("geometricNonlinearity", "linear");
    model.component("comp1").physics("solid").feature("sma1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("sma1").feature("trdir1")
         .set("TransformationDirectionType", "UserDefined");
    model.component("comp1").physics("solid").feature("sma1").feature("trdir1").set("trdir", "pw1(para)");
    model.component("comp1").physics("solid").feature("sma1").create("te1", "ThermalExpansion", 3);

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "-27[degC]"}});

    model.component("comp1").physics("solid").feature("sma1").feature("te1").set("AusteniteMaterial", "mat2");
    model.component("comp1").physics("solid").feature("sma1").feature("te1").set("MartensiteMaterial", "mat3");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(2, 13);
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(16);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(14, 15, 16);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "pload");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(16);

    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "pload", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "intop1(x)-ro(para)", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("solid").feature("ge1").setIndex("CustomDependentVariableUnit", "Pa", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("solid").feature("ge1").setIndex("CustomSourceTermUnit", "m", 0, 0);

    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0.33"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("LagoudasModel", "LagoudasModel", "Lagoudas_model");
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TMs", new String[]{"-28[degC]"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TMf", new String[]{"-43[degC]"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("CM", new String[]{"7.4e6"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TAs", new String[]{"-3[degC]"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TAf", new String[]{"7[degC]"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("CA", new String[]{"7.4e6"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel")
         .set("etrmaxLagoudas", new String[]{"0.056"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"6500"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("ShapeMemoryAlloyAustenite", "ShapeMemoryAlloyAustenite", "Austenite_phase");
    model.component("comp1").material("mat2").propertyGroup("ShapeMemoryAlloyAustenite")
         .set("E_A", new String[]{"55[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("ShapeMemoryAlloyAustenite")
         .set("Cp_A", new String[]{"400"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("ShapeMemoryAlloyThermalExpansionAustenite", "ShapeMemoryAlloyThermalExpansionAustenite", "Thermal_expansion,_austenite_phase");
    model.component("comp1").material("mat2").propertyGroup("ShapeMemoryAlloyThermalExpansionAustenite")
         .set("alpha_A", new String[]{"22e-6"});
    model.component("comp1").material("mat3").propertyGroup()
         .create("ShapeMemoryAlloyMartensite", "ShapeMemoryAlloyMartensite", "Martensite_phase");
    model.component("comp1").material("mat3").propertyGroup("ShapeMemoryAlloyMartensite")
         .set("E_M", new String[]{"46[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("ShapeMemoryAlloyMartensite")
         .set("Cp_M", new String[]{"400"});
    model.component("comp1").material("mat3").propertyGroup()
         .create("ShapeMemoryAlloyThermalExpansionMartensite", "ShapeMemoryAlloyThermalExpansionMartensite", "Thermal_expansion,_martensite_phase");
    model.component("comp1").material("mat3").propertyGroup("ShapeMemoryAlloyThermalExpansionMartensite")
         .set("alpha_M", new String[]{"22e-6"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(13);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(18);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("maxop1").selection().set(12);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().all();
    model.component("comp1").cpl("aveop1").set("frame", "material");

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ro", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ro", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.05,2)", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u9a6c\u6c0f\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "maxop1(solid.xiGp_M)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u8868\u9762\u6700\u5927\u503c", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "aveop1(solid.xiGp_M)", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u4f53\u79ef\u5e73\u5747", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u9a6c\u6c0f\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg2").set("legendpos", "upperleft");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u538b\u529b vs. \u6e29\u5ea6");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3")
         .setIndex("looplevel", new int[]{21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41}, 0);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "pload", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "kPa", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "temperature(para)");
    model.result("pg3").feature("glob1").set("xdataunit", "degC");
    model.result("pg3").feature("glob1").set("xdatadescractive", true);
    model.result("pg3").feature("glob1").set("xdatadescr", "\u6e29\u5ea6");
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);
    model.result("pg3").run();
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", "2*Ns");
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").set("data", "sec1");
    model.result().dataset("arr1").set("fullsize", new int[]{1, 1, 4});
    model.result().dataset("arr1").set("displmethod", "manual");
    model.result().dataset("arr1").set("displ", new String[]{"0", "0", "hs*1.1"});
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\uff0c\u6574\u4e2a\u652f\u67b6");
    model.result("pg4").set("data", "arr1");
    model.result("pg4").run();
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 41, 0);
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u53d8\u5f62\uff0c\u6574\u4e2a\u652f\u67b6");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "solid.xiGp_M");
    model.result("pg5").feature("vol1").set("descr", "\u9a6c\u6c0f\u4f53\u4f53\u79ef\u5206\u6570");
    model.result("pg5").feature("vol1").set("colortable", "Viridis");
    model.result("pg5").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 21, 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 41, 0);
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
    model.result().export("anim1").label("\u53d8\u5f62");
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").set("maxframes", 21);
    model.result().export("anim1").set("frametime", 0.2);
    model.result().export("anim1").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").set("data", "arr1");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("view", "view5");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg6").feature("surf1").feature("mtrl1").set("family", "steel");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg5").run();

    model.title("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u81ea\u81a8\u5f0f\u652f\u67b6");

    model
         .description("\u672c\u6a21\u578b\u7814\u7a76\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u652f\u67b6\u5728\u4f4e\u6e29\u4e0b\u53d1\u751f\u5377\u66f2\uff0c\u7136\u540e\u52a0\u70ed\u81f3\u4eba\u4f53\u4f53\u6e29\u7684\u8fc7\u7a0b\u3002\u7ed3\u679c\u663e\u793a\u5e94\u529b\u5728\u52a0\u70ed\u8fc7\u7a0b\u4e2d\u4e0d\u65ad\u589e\u52a0\uff0c\u4ee5\u53ca\u7531\u6b64\u4f5c\u7528\u4e8e\u52a8\u8109\u5185\u58c1\u7684\u538b\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sma_stent.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
