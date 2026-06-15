/*
 * membrane_airbag_inflation_hyperelastic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:31 by COMSOL 6.3.0.290. */
public class membrane_airbag_inflation_hyperelastic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);

    model.param().label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("para", "0", "\u53c2\u6570");
    model.param().set("th", "1[mm]", "\u539a\u5ea6");
    model.param()
         .set("Ld", "1.2[m]", "\u672a\u5145\u6c14\u5b89\u5168\u6c14\u56ca\u7684\u5bf9\u89d2\u7ebf\u957f\u5ea6");
    model.param().set("L", "Ld/sqrt(2)", "\u957f\u5ea6\u53c2\u6570");
    model.param().set("EE", "588[MPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("Nu", "0.4", "\u6cca\u677e\u6bd4");
    model.param().set("Pmax", "5[kPa]", "\u6700\u5927\u6c14\u538b");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "F");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 100, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int1").setIndex("fununit", "N/m", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("wmax_ref", "22.45[cm]");
    model.component("comp1").variable("var1")
         .descr("wmax_ref", "\u4e2d\u70b9\u7684\u6a2a\u5411\u4f4d\u79fb\uff0c\u53c2\u8003\u503c");
    model.component("comp1").variable("var1").set("u1max_ref", "3.07[cm]");
    model.component("comp1").variable("var1")
         .descr("u1max_ref", "\u89d2\u70b9\u7684\u6a2a\u5411\u4f4d\u79fb\uff0c\u53c2\u8003\u503c");
    model.component("comp1").variable("var1").set("u2max_ref", "11.58[cm]");
    model.component("comp1").variable("var1")
         .descr("u2max_ref", "\u4fa7\u8fb9\u4e2d\u70b9\u7684\u6a2a\u5411\u4f4d\u79fb\uff0c\u53c2\u8003\u503c");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "L/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("pos", new String[]{"L/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");

    model.component("comp1").physics("mbrn").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("mbrn").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("mbrn").feature("hmm1").selection().all();
    model.component("comp1").physics("mbrn").feature("hmm1").set("IsotropicOption", "Enu");
    model.component("comp1").physics("mbrn").feature("hmm1").create("wr1", "Wrinkling", 2);
    model.component("comp1").physics("mbrn").feature("hmm1").feature("wr1").set("termination", "steporresi");
    model.component("comp1").physics("mbrn").feature("to1").set("d", "th");
    model.component("comp1").physics("mbrn").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("mbrn").feature("sym1").selection().set(1, 3);
    model.component("comp1").physics("mbrn").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("mbrn").feature("disp1").selection().set(2, 4);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("mbrn").feature("fl1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl1").set("pressure", "-Pmax*para");
    model.component("comp1").physics("mbrn").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("mbrn").feature("el1").selection().set(2, 4);
    model.component("comp1").physics("mbrn").feature("el1").set("coordinateSystem", "LocalEdgeSystem");
    model.component("comp1").physics("mbrn").feature("el1")
         .set("forceReferenceLength", new String[]{"0", "-F(para)", "0"});
    model.component("comp1").physics("mbrn").create("stb1", "MembraneStabilization", 2);

    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"EE"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"Nu"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 25);
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").set("splitmethod", "center");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.001,1)", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 0.001);
    model.sol("sol1").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 100);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "cm", "cm"}, 0);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xz");
    model.result().dataset("mir1").set("quicky", "0.5*L");
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "yz");
    model.result().dataset("mir2").set("quickx", "0.5*L");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1001, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u4f4d\u79fb (mbrn)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"mbrn.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u4f4d\u79fb (mbrn)");
    model.result("pg1").run();
    model.result("pg1").label("\u6a2a\u5411\u4f4d\u79fb");
    model.result("pg1").set("data", "mir2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").feature("surf1").set("threshold", "none");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mbrn.sp1Gp");
    model.result("pg2").feature("surf1").set("descr", "\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").run();
    model.result("pg2").set("view", "new");
    model.result("pg2").run();

    model.view("view4").set("showgrid", false);

    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbrn.sp2Gp");
    model.result("pg3").feature("surf1").set("descr", "\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u8936\u76b1\u533a");
    model.result("pg4").set("showlegendsmaxmin", false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "mbrn.iswrinkled");
    model.result("pg4").feature("surf1").set("descr", "\u5e26\u8936\u76b1");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("smooth", "none");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u70b9\u4f4d\u79fb");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(2);
    model.result("pg5").feature("ptgr1").set("expr", "w");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "Pmax*para");
    model.result("pg5").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u4e2d\u70b9\u7684\u6a2a\u5411\u4f4d\u79fb", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").selection().set(4);
    model.result("pg5").feature("ptgr2").set("expr", "-u");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u89d2\u70b9\u7684\u6a2a\u5411\u4f4d\u79fb", 0);
    model.result("pg5").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").selection().set(3);
    model.result("pg5").feature("ptgr3")
         .setIndex("legends", "\u4fa7\u8fb9\u4e2d\u70b9\u7684\u6a2a\u5411\u4f4d\u79fb", 0);
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset1");
    model.result("pg5").feature("glob1").setIndex("looplevelinput", "last", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "wmax_ref", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "Pmax*para");
    model.result("pg5").feature("glob1").set("xdataunit", "kPa");
    model.result("pg5").feature("glob1").set("linecolor", "magenta");
    model.result("pg5").feature("glob1").set("linemarker", "square");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").feature("glob1").set("markers", 12);
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u6700\u540e\u4e00\u6b65\u7684\u53c2\u8003\u503c", 0);
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").setIndex("expr", "u1max_ref", 0);
    model.result("pg5").feature("glob2").set("legend", false);
    model.result("pg5").feature().duplicate("glob3", "glob2");
    model.result("pg5").run();
    model.result("pg5").feature("glob3").setIndex("expr", "u2max_ref", 0);
    model.result("pg5").feature("glob3")
         .setIndex("descr", "\u4fa7\u8fb9\u4e2d\u70b9\u7684\u603b\u4f4d\u79fb\uff0c\u53c2\u8003\u503c", 0);
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u70b9\u4f4d\u79fb");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u81a8\u80c0\u538b\u529b (kPa)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u4f4d\u79fb (cm)");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymax", 30);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u70b9\u5e94\u529b");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u81a8\u80c0\u538b\u529b (kPa)");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(2);
    model.result("pg6").feature("ptgr1").set("expr", "mbrn.sp1");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "Pmax*para");
    model.result("pg6").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u4e2d\u70b9\u7684\u7b2c\u4e00\u4e3b\u5e94\u529b", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg4").run();

    model.title("\u65b9\u5f62\u8d85\u5f39\u6027\u6c14\u56ca\u7684\u81a8\u80c0");

    model
         .description("\u4e00\u4e2a\u7531 neo-Hookean \u8d85\u5f39\u6027\u6750\u6599\u5236\u6210\u7684\u65b9\u5f62\u5b89\u5168\u6c14\u56ca\u5728\u6052\u5b9a\u6c14\u538b\u4e0b\u5145\u6c14\u3002\u5728\u5b89\u5168\u6c14\u56ca\u7684\u67d0\u4e9b\u533a\u57df\uff0c\u968f\u7740\u8d1f\u4e3b\u5e94\u529b\u7684\u53d1\u5c55\uff0c\u6750\u6599\u4f1a\u8d77\u76b1\u3002\n\n\u5728\u672c\u4f8b\u4e2d\uff0c\u5f20\u529b\u573a\u7406\u8bba\u4e2d\u7684\u8936\u76b1\u819c\u6a21\u578b\u5bf9\u5145\u6c14\u819c\u4e2d\u7684\u5e94\u529b\u5206\u5e03\u548c\u8936\u76b1\u6a21\u5f0f\u7ed9\u51fa\u4e86\u6b63\u786e\u7684\u63cf\u8ff0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("membrane_airbag_inflation_hyperelastic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
