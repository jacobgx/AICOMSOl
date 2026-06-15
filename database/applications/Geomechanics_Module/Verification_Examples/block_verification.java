/*
 * block_verification.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:15 by COMSOL 6.3.0.290. */
public class block_verification {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("Disp", "0[mm]");
    model.param().descr("Disp", "\u4f4d\u79fb\u53c2\u6570");
    model.param().set("X_stress", "-3e5[Pa]");
    model.param().descr("X_stress", "\u539f\u4f4d\u5e94\u529b\uff0cxx \u5206\u91cf");
    model.param().set("Y_stress", "-2e5[Pa]");
    model.param().descr("Y_stress", "\u539f\u4f4d\u5e94\u529b\uff0cyy \u5206\u91cf");
    model.param().set("Z_stress", "-1e5[Pa]");
    model.param().descr("Z_stress", "\u539f\u4f4d\u5e94\u529b\uff0czz \u5206\u91cf");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(4);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Forcez", "intop1(solid.sz)/intop1(1)-Z_stress");
    model.component("comp1").variable("var1").descr("Forcez", "\u8f74\u5411\u529b");
    model.component("comp1").variable("var1")
         .set("szz_th", "(2*solid.cohesion*cos(solid.phis)-Y_stress*(1+sin(solid.phis)))/(sin(solid.phis)-1)");
    model.component("comp1").variable("var1").descr("szz_th", "\u7406\u8bba\u5c48\u670d\u5e94\u529b");

    model.component("comp1").physics("solid").feature("lemm1").create("sopl1", "SoilPlasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("failureCriterion", "MohrCoulomb");
    model.component("comp1").physics("solid").feature("lemm1").create("exs1", "ExternalStress", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("StressInputType", "InSituStress");
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("sins", new String[]{"X_stress", "0", "0", "0", "Y_stress", "0", "0", "0", "Z_stress"});
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2, 3);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(4);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-Disp", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"207e6"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2000"});
    model.component("comp1").material("mat1").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.component("comp1").material("mat1").propertyGroup("Soil").set("cohesion0", new String[]{"70e3"});
    model.component("comp1").material("mat1").propertyGroup("Soil").set("phis", new String[]{"30[deg]"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "8*range(0,0.05,1)", 0);
    model.study("std1").feature("stat").setIndex("punit", "mm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 21, 0);
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
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("solutionintitle", false);
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("relpadding", 1);
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("data", "dset1");
    model.result("pg1").feature("vol1").setIndex("looplevel", 1, 0);
    model.result("pg1").feature("vol1").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature().remove("def");
    model.result("pg1").feature("vol1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").feature("vol2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").setIndex("looplevel", 6, 0);
    model.result("pg1").feature("vol2").set("titletype", "none");
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature("vol2").set("arrayindex", 1);
    model.result("pg1").feature().duplicate("vol3", "vol2");
    model.result("pg1").feature("vol3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol3").setIndex("looplevel", 16, 0);
    model.result("pg1").feature("vol3").set("arrayindex", 2);
    model.result("pg1").feature().duplicate("vol4", "vol3");
    model.result("pg1").feature("vol4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("vol4").setIndex("looplevel", 21, 0);
    model.result("pg1").feature("vol4").set("arrayindex", 3);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("arraydim", "1");
    model.result("pg1").feature("arws1").set("data", "dset1");
    model.result("pg1").feature("arws1").setIndex("looplevel", 1, 0);
    model.result("pg1").feature("arws1").set("expr", new String[]{"0", "0", "Forcez"});
    model.result("pg1").feature("arws1").set("titletype", "none");
    model.result("pg1").feature("arws1").set("arrowcount", 40);
    model.result("pg1").feature("arws1").set("arrowbase", "head");
    model.result("pg1").feature("arws1").set("color", "custom");
    model.result("pg1").feature("arws1").set("customcolor", new double[]{0.501960813999176, 0.250980406999588, 0});
    model.result("pg1").feature("arws1").set("manualindexing", true);
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(4);
    model.result("pg1").feature("arws1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws2", "arws1");
    model.result("pg1").feature("arws2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").setIndex("looplevel", 6, 0);
    model.result("pg1").feature("arws2").set("arrayindex", 1);
    model.result("pg1").feature("arws2").set("scaleactive", true);
    model.result("pg1").feature("arws2").set("scale", "4E-4");
    model.result("pg1").feature("arws2").set("inheritplot", "arws1");
    model.result("pg1").feature("arws2").set("inheritarrowscale", false);
    model.result("pg1").feature().duplicate("arws3", "arws2");
    model.result("pg1").feature("arws3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("arws3").setIndex("looplevel", 16, 0);
    model.result("pg1").feature("arws3").set("arrayindex", 2);
    model.result("pg1").feature("arws3").set("inheritplot", "arws2");
    model.result("pg1").feature().duplicate("arws4", "arws3");
    model.result("pg1").feature("arws4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("arws4").setIndex("looplevel", 21, 0);
    model.result("pg1").feature("arws4").set("arrayindex", 3);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("arraydim", "1");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.5, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.1, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "$\\delta$=0[mm]", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 2.5, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.1, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "$\\delta$=2[mm]", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 4.5, 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.1, 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "$\\delta$=6[mm]", 2, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 6.5, 3, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.1, 3, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 3, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "$\\delta$=8[mm]", 3, 3);
    model.result("pg1").feature("tlan1").set("latexmarkup", true);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b vs. \u4f4d\u79fb");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5782\u76f4\u4f4d\u79fb (mm)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e94\u529b (kPa)");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "solid.sGpxx");
    model.result("pg2").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "Disp");
    model.result("pg2").feature("ptgr1").set("xdataunit", "mm");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "solid.sGpyy");
    model.result("pg2").feature("ptgr2").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("expr", "solid.sGpzz");
    model.result("pg2").feature("ptgr3").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr4", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("expr", "szz_th");
    model.result("pg2").feature("ptgr4").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr4").set("linecolor", "magenta");
    model.result("pg2").feature("ptgr4").setIndex("legends", "\u7406\u8bba\u5c48\u670d\u5e94\u529b", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u539f\u4f4d\u5e94\u529b");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u539f\u4f4d\u5e94\u529b (kPa)");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", "1");
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").feature("vol1").set("color", "gray");
    model.result("pg3").feature("vol1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("tran1").set("transparency", 0.7);
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1")
         .set("expr", new String[]{"solid.SinsXX*solid.nX+solid.SinsXY*solid.nY+solid.SinsXZ*solid.nZ", "v", "w"});
    model.result("pg3").feature("arws1")
         .setIndex("expr", "solid.SinsXY*solid.nX+solid.SinsYY*solid.nY+solid.SinsYZ*solid.nZ", 1);
    model.result("pg3").feature("arws1")
         .setIndex("expr", "solid.SinsXZ*solid.nX+solid.SinsXZ*solid.nY+solid.SinsZZ*solid.nZ", 2);
    model.result("pg3").feature("arws1").set("arrowbase", "head");
    model.result("pg3").feature("arws1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("col1").set("colordata", "arrowlength");
    model.result("pg3").feature("arws1").feature("col1").set("coloring", "gradient");
    model.result("pg3").feature("arws1").feature("col1").set("topcolor", "red");
    model.result("pg3").run();
    model.result("pg2").run();

    model.title("\u5757\u4f53\u9a8c\u8bc1");

    model
         .description("\u672c\u9a8c\u8bc1\u793a\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u53d7\u9884\u5e94\u529b\u5757\u4f53\u5728\u5355\u8f74\u538b\u7f29\u6761\u4ef6\u4e0b\u7684\u60c5\u51b5\u3002\u901a\u8fc7\u8003\u8651\u9884\u5e94\u529b\u548c\u5355\u8f74\u538b\u7f29\u7684\u7b80\u5355\u60c5\u5f62\uff0c\u6211\u4eec\u53ef\u4ee5\u901a\u8fc7\u5206\u6790\u6765\u786e\u5b9a\u6750\u6599\u7684\u5c48\u670d\u5e94\u529b\u3002\u672c\u4f8b\u57fa\u4e8e\u83ab\u5c14-\u5e93\u4ed1\u5c48\u670d\u51c6\u5219\uff0c\u5c06\u5757\u4f53\u4f5c\u4e3a\u5f39\u6027-\u7406\u60f3\u5851\u6027\u6750\u6599\u8fdb\u884c\u5efa\u6a21\u3002");

    model.label("block_verification.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
