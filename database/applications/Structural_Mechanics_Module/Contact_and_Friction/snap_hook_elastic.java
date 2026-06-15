/*
 * snap_hook_elastic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:03 by COMSOL 6.3.0.290. */
public class snap_hook_elastic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").view("view1").set("showgrid", false);

    model.param().set("disp_max", "4.6[mm]");
    model.param().descr("disp_max", "\u5f39\u7c27\u6263\u6700\u5927\u4f4d\u79fb");
    model.param().set("disp", "0[m]");
    model.param().descr("disp", "\u5f39\u7c27\u6263\u6307\u5b9a\u4f4d\u79fb");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "snap_hook_elastic.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("rot1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").scaleUnitValue(true);
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6e90\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(1, 4, 6, 7, 8);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u76ee\u6807\u8fb9\u754c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(14, 15, 20, 22, 23);

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().named("sel1");
    model.component("comp1").pair("p1").destination().named("sel2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"10[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.35"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});

    model.component("comp1").physics("solid").selection().set(2, 3);
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", "1/10");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(30);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-disp", 0);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(13, 19);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(27, 28, 39, 48);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(31, 45);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("conv1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").run("conv1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(25);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hauto", 9);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(5, 13);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(30);
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "disp_max", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp_max", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.2,1e-2,1)*disp_max", 0);
    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 81, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 64, 0);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 81, 0);
    model.result("pg2").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 1);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.dcnt1.Tnx", "solid.dcnt1.Tny", "solid.dcnt1.Tnz"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("gporder", 4);
    model.result("pg2").feature("arws1").label("\u63a5\u89e6 1, \u538b\u529b");
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").set("color", "green");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "green");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 64, 0);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6700\u5c0f\u95f4\u9699\u8ddd\u79bb");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "solid.gapmin_p1*(solid.gapmin_p1<0)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "um", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u6700\u5c0f\u95f4\u9699\u8ddd\u79bb", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "disp");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u53cd\u4f5c\u7528\u529b");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "-2*intop1(solid.RFx)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u603b\u529b", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "disp");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u5f39\u6027\u5f39\u7c27\u6263\u7684\u63a5\u89e6\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5f39\u7c27\u6263\u63d2\u5165\u5176\u51f9\u69fd\u7684\u8fc7\u7a0b\uff0c\u5176\u4e2d\u4f7f\u7528\u7ebf\u5f39\u6027\u6750\u6599\u6a21\u578b\u5bf9\u5f39\u7c27\u6263\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("snap_hook_elastic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
