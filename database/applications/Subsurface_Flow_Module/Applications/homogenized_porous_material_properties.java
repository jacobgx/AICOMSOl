/*
 * homogenized_porous_material_properties.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:27 by COMSOL 6.3.0.290. */
public class homogenized_porous_material_properties {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("geomVar", "1");
    model.param().descr("geomVar", "Geometry parameter");
    model.param().set("wm", "1[mm]");
    model.param().descr("wm", "Width of unit cell");
    model.param().set("dm", "1[mm]");
    model.param().descr("dm", "Depth of unit cell");
    model.param().set("hm", "1[mm]");
    model.param().descr("hm", "Height of unit cell");
    model.param().set("V", "wm*dm*hm");
    model.param().descr("V", "Reference volume of unit cell");
    model.param().set("l_min", "min(hm,min(wm,dm))");
    model.param().descr("l_min", "Minimum unit cell side length");
    model.param().set("l_diagonal", "sqrt(wm^2+dm^2+hm^2)");
    model.param().descr("l_diagonal", "Unit cell diagonal");
    model.param().set("rho_f", "1000[kg/m^3]");
    model.param().descr("rho_f", "Fluid density");
    model.param().set("mu_f", "1e-3[Pa*s]");
    model.param().descr("mu_f", "Fluid viscosity");
    model.param().set("p_in", "1[Pa]");
    model.param().descr("p_in", "Inlet pressure");
    model.param().create("par2");
    model.param("par2").set("d1_1", "0.6[mm]");
    model.param("par2").descr("d1_1", "Particle diameter");
    model.param("par2").set("d1_1_max", "l_min");
    model.param("par2").descr("d1_1_max", "Maximum allowed particle diameter");
    model.param("par2").set("epsilon_p1", "1-(4/3*pi*(0.5*d1_1)^3)/V");
    model.param("par2").descr("epsilon_p1", "Porosity");
    model.param().create("par3");
    model.param("par3").set("d1_2", "0.6[mm]");
    model.param("par3").descr("d1_2", "Diameter of center particle");
    model.param("par3").set("ratio_d1_2", "1");
    model.param("par3").descr("ratio_d1_2", "Particle size ratio");
    model.param("par3").set("d2_2", "ratio_d1_2*d1_2");
    model.param("par3").descr("d2_2", "Diameter of corner particle");
    model.param("par3").set("d1_2_max", "min(min(l_min,l_min/ratio_d1_2),l_diagonal/(1+ratio_d1_2))");
    model.param("par3").descr("d1_2_max", "Maximum allowed center particle diameter");
    model.param("par3").set("ratio_2_max", "(l_diagonal-d1_2)/d1_2");
    model.param("par3").descr("ratio_2_max", "Maximum allowed ratio");
    model.param("par3").set("epsilon_p2", "1-(1/6*pi*d1_2^3*(1+ratio_d1_2^3))/V");
    model.param("par3").descr("epsilon_p2", "Porosity");
    model.param().create("par4");
    model.param("par4").set("d1_3", "0.4[mm]");
    model.param("par4").descr("d1_3", "Diameter of center particle");
    model.param("par4").set("ratio_d1_3", "1");
    model.param("par4").descr("ratio_d1_3", "Particle size ratio");
    model.param("par4").set("d2_3", "ratio_d1_3*d1_3");
    model.param("par4").descr("d2_3", "Diameter of corner particle");
    model.param("par4").set("diag_help", "min(min(sqrt(wm^2+dm^2),sqrt(wm^2+hm^2)),sqrt(dm^2+hm^2))");
    model.param("par4").descr("diag_help", "Minimum face diagonal");
    model.param("par4").set("d1_3_max", "diag_help/(1+ratio_d1_3)");
    model.param("par4").descr("d1_3_max", "Maximum allowed center particle diameter");
    model.param("par4").set("ratio_3_max", "(diag_help-d1_3)/d1_3");
    model.param("par4").descr("ratio_3_max", "Maximum allowed ratio");
    model.param("par4").set("epsilon_p3", "1-(1/6*pi*d1_3^3*(1+3*ratio_d1_3^3))/V");
    model.param("par4").descr("epsilon_p3", "Porosity");
    model.param().create("par5");
    model.param("par5").set("d1_4", "0.75[mm]");
    model.param("par5").descr("d1_4", "Fiber diameter");
    model.param("par5").set("d1_4_max", "min(dm,hm)");
    model.param("par5").descr("d1_4_max", "Maximum fiber diameter");
    model.param("par5").set("epsilon_p4", "1-(pi*(0.5*d1_4)^2*wm)/V");
    model.param("par5").descr("epsilon_p4", "Porosity");
    model.param().create("par6");
    model.param("par6").set("d1_5", "0.6[mm]");
    model.param("par6").descr("d1_5", "Diameter of centered fiber");
    model.param("par6").set("ratio_d1_5", "1");
    model.param("par6").descr("ratio_d1_5", "Fiber size ratio");
    model.param("par6").set("d2_5", "d1_5*ratio_d1_5");
    model.param("par6").descr("d2_5", "Diameter of corner fiber");
    model.param("par6").set("d1_5_max", "sqrt(dm^2+hm^2)/(1+ratio_d1_5)");
    model.param("par6").descr("d1_5_max", "Maximum allowed center fiber diameter");
    model.param("par6").set("ratio_5_max", "(sqrt(dm^2+hm^2)-d1_5)/d1_5");
    model.param("par6").descr("ratio_5_max", "Maximum allowed ratio");
    model.param("par6").set("epsilon_p5", "1-((1+ratio_d1_5^2)*pi*(0.5*d1_5)^2*wm)/V");
    model.param("par6").descr("epsilon_p5", "Porosity");
    model.param().create("par7");
    model.param("par7").set("d1_6", "2.5[mm]");
    model.param("par7").descr("d1_6", "Strut diameter");
    model.param("par7").set("L", "50[mm]");
    model.param("par7").descr("L", "Cell side length");
    model.param("par7").set("d1_6_max", "0.2*L");
    model.param("par7").descr("d1_6_max", "Maximum strut diameter");
    model.param().create("par8");
    model.param("par8").set("scale_d1", "d1_1/l_min");
    model.param("par8").descr("scale_d1", "Scale factor for inner particle/fiber");
    model.param("par8").set("ratio_wmdm", "wm/dm");
    model.param("par8").descr("ratio_wmdm", "Ratio of width to depth of unit cell");
    model.param("par8").set("ratio_hmdm", "hm/dm");
    model.param("par8").descr("ratio_hmdm", "Ratio of height to depth of unit cell");
    model.param("par8").set("ratio_dL", "d1_6/L");
    model.param("par8").descr("ratio_dL", "Ratio of strut diameter to unit cell length");

    model.component("comp1").geom("geom1")
         .insertFile("homogenized_porous_material_properties_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("spf").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "p_in");
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_csel15_bnd");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl2").set("p0", "p_in");
    model.component("comp1").physics("spf").feature("inl2").selection().named("geom1_csel18_bnd");
    model.component("comp1").physics("spf").feature().duplicate("inl3", "inl2");
    model.component("comp1").physics("spf").feature("inl3").selection().named("geom1_csel19_bnd");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_csel16_bnd");
    model.component("comp1").physics("spf").create("out2", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out2").selection().named("geom1_csel20_bnd");
    model.component("comp1").physics("spf").feature().duplicate("out3", "out2");
    model.component("comp1").physics("spf").feature("out3").selection().named("geom1_csel21_bnd");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_csel17_bnd");
    model.component("comp1").physics("spf").create("sym2", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym2").selection().named("geom1_csel22_bnd");
    model.component("comp1").physics("spf").create("sym3", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym3").selection().named("geom1_csel23_bnd");

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().named("geom1_csel24_dom");
    model.component("comp1").massProp("mass1").set("createMass", false);
    model.component("comp1").massProp("mass1").set("createMomentOfInertia", false);
    model.component("comp1").massProp("mass1").set("createPrincipalInertia", false);
    model.component("comp1").massProp("mass1").set("createCenterOfMass", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("por", "mass1.volume/V");
    model.component("comp1").variable("var1").descr("por", "Porosity");
    model.component("comp1").variable("var1").set("k_xx", "spf.out1.volumeFlowRate*mu_f*wm/(dm*hm*p_in)");
    model.component("comp1").variable("var1").descr("k_xx", "Permeability, xx-component");
    model.component("comp1").variable("var1").set("k_yy", "spf.out2.volumeFlowRate*mu_f*dm/(wm*hm*p_in)");
    model.component("comp1").variable("var1").descr("k_yy", "Permeability, yy-component");
    model.component("comp1").variable("var1").set("k_zz", "spf.out3.volumeFlowRate*mu_f*hm/(wm*dm*p_in)");
    model.component("comp1").variable("var1").descr("k_zz", "Permeability, zz-component");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "wm/2 1.3*wm+wm/2 2.6*wm+wm/2");
    model.result("pg1").feature("mslc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new double[]{0.04, 0.13});
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "p");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "GrayScale");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg1").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(6, 7, 8, 9, 10, 11, 12, 13, 20, 21, 22, 23, 24, 25, 26, 27, 34, 35, 36, 37, 38, 39, 40, 41);
    model.result().dataset("surf1")
         .set("defaultPlotIDs", new String[]{"ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond2/pcond2/pcond1/pg4|spf"});
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond2/pcond2/pcond1/pg4");
    model.result("pg2").set("weight", 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").run();
    model.result().dataset("surf1").selection().named("geom1_unisel1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature().remove("tran1");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "d1_1", 0);
    model.result().numerical("gev1").setIndex("expr", "wm", 1);
    model.result().numerical("gev1").setIndex("expr", "dm", 2);
    model.result().numerical("gev1").setIndex("expr", "hm", 3);
    model.result().numerical("gev1").setIndex("expr", "por", 4);
    model.result().numerical("gev1").setIndex("expr", "k_xx", 5);
    model.result().numerical("gev1").setIndex("expr", "k_yy", 6);
    model.result().numerical("gev1").setIndex("expr", "k_zz", 7);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("homogenized_porous_material_properties_data1.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").importData("homogenized_porous_material_properties_data2.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").importData("homogenized_porous_material_properties_data3.txt");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").importData("homogenized_porous_material_properties_data4.txt");
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").importData("homogenized_porous_material_properties_data5.txt");
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").importData("homogenized_porous_material_properties_data6.txt");

    model.func().create("dnn1", "DNN");
    model.func("dnn1").set("source", "resultTable");
    model.func("dnn1").set("resultTable", "tbl2");
    model.func("dnn1").setEntry("columnType", "col5", "none");
    model.func("dnn1").setEntry("columnType", "col6", "none");
    model.func("dnn1").setEntry("columnType", "col7", "none");
    model.func("dnn1").setEntry("columnType", "col8", "none");
    model.func("dnn1").setEntry("columnType", "col9", "none");
    model.func("dnn1").setEntry("columnType", "col10", "none");
    model.func("dnn1").setEntry("columnType", "col11", "none");
    model.func("dnn1").setEntry("funcs", "col12", "dnn1_kxx");
    model.func("dnn1").setEntry("funcs", "col13", "dnn1_kyy");
    model.func("dnn1").setEntry("funcs", "col14", "dnn1_kzz");
    model.func("dnn1").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn1").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn1").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn1")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn1").move("layertype", new int[]{2}, -1);
    model.func("dnn1").move("outfeatures", new int[]{2}, -1);
    model.func("dnn1").move("activation", new int[]{2}, -1);
    model.func("dnn1").move("layerDescription", new int[]{2}, -1);
    model.func("dnn1").setIndex("outfeatures", "48", 1);
    model.func("dnn1").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn1").set("outfeatures", new int[]{1, 48, 1, 1});
    model.func("dnn1").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn1")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn1").move("layertype", new int[]{3}, -1);
    model.func("dnn1").move("outfeatures", new int[]{3}, -1);
    model.func("dnn1").move("activation", new int[]{3}, -1);
    model.func("dnn1").move("layerDescription", new int[]{3}, -1);
    model.func("dnn1").setIndex("outfeatures", "24", 2);
    model.func("dnn1").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.func("dnn1").set("outfeatures", new int[]{1, 48, 24, 1, 1});
    model.func("dnn1").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn1")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=24, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn1").move("layertype", new int[]{4}, -1);
    model.func("dnn1").move("outfeatures", new int[]{4}, -1);
    model.func("dnn1").move("activation", new int[]{4}, -1);
    model.func("dnn1").move("layerDescription", new int[]{4}, -1);
    model.func("dnn1").setIndex("outfeatures", "12", 3);
    model.func("dnn1").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn1").set("outfeatures", new int[]{1, 48, 24, 12, 1, 1});
    model.func("dnn1").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn1")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=24, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=12, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn1").move("layertype", new int[]{5}, -1);
    model.func("dnn1").move("outfeatures", new int[]{5}, -1);
    model.func("dnn1").move("activation", new int[]{5}, -1);
    model.func("dnn1").move("layerDescription", new int[]{5}, -1);
    model.func("dnn1").setIndex("outfeatures", "6", 4);
    model.func("dnn1").set("batchsize", 256);
    model.func("dnn1").set("epochs", 5000);
    model.func("dnn1").run();
    model.func().create("dnn2", "DNN");
    model.func("dnn2").set("source", "resultTable");
    model.func("dnn2").set("resultTable", "tbl3");
    model.func("dnn2").setEntry("columnType", "col6", "none");
    model.func("dnn2").setEntry("columnType", "col7", "none");
    model.func("dnn2").setEntry("columnType", "col8", "none");
    model.func("dnn2").setEntry("columnType", "col9", "none");
    model.func("dnn2").setEntry("columnType", "col10", "none");
    model.func("dnn2").setEntry("columnType", "col11", "none");
    model.func("dnn2").setEntry("columnType", "col12", "none");
    model.func("dnn2").setEntry("funcs", "col13", "dnn2_kxx");
    model.func("dnn2").setEntry("funcs", "col14", "dnn2_kyy");
    model.func("dnn2").setEntry("funcs", "col15", "dnn2_kzz");
    model.func("dnn2").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn2").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn2").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn2")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn2").move("layertype", new int[]{2}, -1);
    model.func("dnn2").move("outfeatures", new int[]{2}, -1);
    model.func("dnn2").move("activation", new int[]{2}, -1);
    model.func("dnn2").move("layerDescription", new int[]{2}, -1);
    model.func("dnn2").setIndex("outfeatures", "60", 1);
    model.func("dnn2").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn2").set("outfeatures", new int[]{1, 60, 1, 1});
    model.func("dnn2").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn2")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn2").move("layertype", new int[]{3}, -1);
    model.func("dnn2").move("outfeatures", new int[]{3}, -1);
    model.func("dnn2").move("activation", new int[]{3}, -1);
    model.func("dnn2").move("layerDescription", new int[]{3}, -1);
    model.func("dnn2").setIndex("outfeatures", "30", 2);
    model.func("dnn2").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.func("dnn2").set("outfeatures", new int[]{1, 60, 30, 1, 1});
    model.func("dnn2").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn2")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn2").move("layertype", new int[]{4}, -1);
    model.func("dnn2").move("outfeatures", new int[]{4}, -1);
    model.func("dnn2").move("activation", new int[]{4}, -1);
    model.func("dnn2").move("layerDescription", new int[]{4}, -1);
    model.func("dnn2").setIndex("outfeatures", "15", 3);
    model.func("dnn2").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn2").set("outfeatures", new int[]{1, 60, 30, 15, 1, 1});
    model.func("dnn2").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn2")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=15, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn2").move("layertype", new int[]{5}, -1);
    model.func("dnn2").move("outfeatures", new int[]{5}, -1);
    model.func("dnn2").move("activation", new int[]{5}, -1);
    model.func("dnn2").move("layerDescription", new int[]{5}, -1);
    model.func("dnn2").setIndex("outfeatures", "12", 4);
    model.func("dnn2").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn2").set("outfeatures", new int[]{1, 60, 30, 15, 12, 1, 1});
    model.func("dnn2").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn2")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=15, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=12, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn2").move("layertype", new int[]{6}, -1);
    model.func("dnn2").move("outfeatures", new int[]{6}, -1);
    model.func("dnn2").move("activation", new int[]{6}, -1);
    model.func("dnn2").move("layerDescription", new int[]{6}, -1);
    model.func("dnn2").setIndex("outfeatures", "6", 5);
    model.func("dnn2")
         .set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn2").set("outfeatures", new int[]{1, 60, 30, 15, 12, 6, 1, 1});
    model.func("dnn2")
         .set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn2")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=15, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=12, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=6, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn2").move("layertype", new int[]{7}, -1);
    model.func("dnn2").move("outfeatures", new int[]{7}, -1);
    model.func("dnn2").move("activation", new int[]{7}, -1);
    model.func("dnn2").move("layerDescription", new int[]{7}, -1);
    model.func("dnn2").setIndex("outfeatures", "3", 6);
    model.func("dnn2").set("batchsize", 256);
    model.func("dnn2").set("epochs", 5000);
    model.func("dnn2").run();
    model.func().create("dnn3", "DNN");
    model.func("dnn3").set("source", "resultTable");
    model.func("dnn3").set("resultTable", "tbl4");
    model.func("dnn3").setEntry("columnType", "col6", "none");
    model.func("dnn3").setEntry("columnType", "col7", "none");
    model.func("dnn3").setEntry("columnType", "col8", "none");
    model.func("dnn3").setEntry("columnType", "col9", "none");
    model.func("dnn3").setEntry("columnType", "col10", "none");
    model.func("dnn3").setEntry("columnType", "col11", "none");
    model.func("dnn3").setEntry("columnType", "col12", "none");
    model.func("dnn3").setEntry("funcs", "col13", "dnn3_kxx");
    model.func("dnn3").setEntry("funcs", "col14", "dnn3_kyy");
    model.func("dnn3").setEntry("funcs", "col15", "dnn3_kzz");
    model.func("dnn3").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn3").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn3").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn3")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn3").move("layertype", new int[]{2}, -1);
    model.func("dnn3").move("outfeatures", new int[]{2}, -1);
    model.func("dnn3").move("activation", new int[]{2}, -1);
    model.func("dnn3").move("layerDescription", new int[]{2}, -1);
    model.func("dnn3").setIndex("outfeatures", "60", 1);
    model.func("dnn3").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn3").set("outfeatures", new int[]{1, 60, 1, 1});
    model.func("dnn3").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn3")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn3").move("layertype", new int[]{3}, -1);
    model.func("dnn3").move("outfeatures", new int[]{3}, -1);
    model.func("dnn3").move("activation", new int[]{3}, -1);
    model.func("dnn3").move("layerDescription", new int[]{3}, -1);
    model.func("dnn3").setIndex("outfeatures", "30", 2);
    model.func("dnn3").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.func("dnn3").set("outfeatures", new int[]{1, 60, 30, 1, 1});
    model.func("dnn3").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn3")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn3").move("layertype", new int[]{4}, -1);
    model.func("dnn3").move("outfeatures", new int[]{4}, -1);
    model.func("dnn3").move("activation", new int[]{4}, -1);
    model.func("dnn3").move("layerDescription", new int[]{4}, -1);
    model.func("dnn3").setIndex("outfeatures", "15", 3);
    model.func("dnn3").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn3").set("outfeatures", new int[]{1, 60, 30, 15, 1, 1});
    model.func("dnn3").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn3")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=15, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn3").move("layertype", new int[]{5}, -1);
    model.func("dnn3").move("outfeatures", new int[]{5}, -1);
    model.func("dnn3").move("activation", new int[]{5}, -1);
    model.func("dnn3").move("layerDescription", new int[]{5}, -1);
    model.func("dnn3").setIndex("outfeatures", "12", 4);
    model.func("dnn3").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn3").set("outfeatures", new int[]{1, 60, 30, 15, 12, 1, 1});
    model.func("dnn3").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn3")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=5", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=60, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=30, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=15, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=12, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn3").move("layertype", new int[]{6}, -1);
    model.func("dnn3").move("outfeatures", new int[]{6}, -1);
    model.func("dnn3").move("activation", new int[]{6}, -1);
    model.func("dnn3").move("layerDescription", new int[]{6}, -1);
    model.func("dnn3").setIndex("outfeatures", "6", 5);
    model.func("dnn3").set("batchsize", 256);
    model.func("dnn3").set("epochs", 5000);
    model.func("dnn3").run();
    model.func().create("dnn4", "DNN");
    model.func("dnn4").set("source", "resultTable");
    model.func("dnn4").set("resultTable", "tbl5");
    model.func("dnn4").setEntry("columnType", "col2", "none");
    model.func("dnn4").setEntry("columnType", "col5", "none");
    model.func("dnn4").setEntry("columnType", "col6", "none");
    model.func("dnn4").setEntry("columnType", "col7", "none");
    model.func("dnn4").setEntry("columnType", "col8", "none");
    model.func("dnn4").setEntry("columnType", "col9", "none");
    model.func("dnn4").setEntry("columnType", "col10", "none");
    model.func("dnn4").setEntry("funcs", "col11", "dnn4_kxx");

    return model;
  }

  public static Model run2(Model model) {
    model.func("dnn4").setEntry("funcs", "col12", "dnn4_kyy");
    model.func("dnn4").setEntry("funcs", "col13", "dnn4_kzz");
    model.func("dnn4").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn4").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn4").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn4")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=3", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn4").move("layertype", new int[]{2}, -1);
    model.func("dnn4").move("outfeatures", new int[]{2}, -1);
    model.func("dnn4").move("activation", new int[]{2}, -1);
    model.func("dnn4").move("layerDescription", new int[]{2}, -1);
    model.func("dnn4").setIndex("outfeatures", "48", 1);
    model.func("dnn4").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn4").set("outfeatures", new int[]{1, 48, 1, 1});
    model.func("dnn4").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn4")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=3", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn4").move("layertype", new int[]{3}, -1);
    model.func("dnn4").move("outfeatures", new int[]{3}, -1);
    model.func("dnn4").move("activation", new int[]{3}, -1);
    model.func("dnn4").move("layerDescription", new int[]{3}, -1);
    model.func("dnn4").setIndex("outfeatures", "24", 2);
    model.func("dnn4").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.func("dnn4").set("outfeatures", new int[]{1, 48, 24, 1, 1});
    model.func("dnn4").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn4")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=3", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=24, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn4").move("layertype", new int[]{4}, -1);
    model.func("dnn4").move("outfeatures", new int[]{4}, -1);
    model.func("dnn4").move("activation", new int[]{4}, -1);
    model.func("dnn4").move("layerDescription", new int[]{4}, -1);
    model.func("dnn4").setIndex("outfeatures", "12", 3);
    model.func("dnn4").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn4").set("outfeatures", new int[]{1, 48, 24, 12, 1, 1});
    model.func("dnn4").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn4")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=3", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=24, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=12, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn4").move("layertype", new int[]{5}, -1);
    model.func("dnn4").move("outfeatures", new int[]{5}, -1);
    model.func("dnn4").move("activation", new int[]{5}, -1);
    model.func("dnn4").move("layerDescription", new int[]{5}, -1);
    model.func("dnn4").setIndex("outfeatures", "6", 4);
    model.func("dnn4").set("batchsize", 256);
    model.func("dnn4").set("epochs", 5000);
    model.func("dnn4").run();
    model.func().create("dnn5", "DNN");
    model.func("dnn5").set("source", "resultTable");
    model.func("dnn5").set("resultTable", "tbl6");
    model.func("dnn5").setEntry("columnType", "col2", "none");
    model.func("dnn5").setEntry("columnType", "col6", "none");
    model.func("dnn5").setEntry("columnType", "col7", "none");
    model.func("dnn5").setEntry("columnType", "col8", "none");
    model.func("dnn5").setEntry("columnType", "col9", "none");
    model.func("dnn5").setEntry("columnType", "col10", "none");
    model.func("dnn5").setEntry("columnType", "col11", "none");
    model.func("dnn5").setEntry("funcs", "col12", "dnn5_kxx");
    model.func("dnn5").setEntry("funcs", "col13", "dnn5_kyy");
    model.func("dnn5").setEntry("funcs", "col14", "dnn5_kzz");
    model.func("dnn5").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn5").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn5").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn5")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn5").move("layertype", new int[]{2}, -1);
    model.func("dnn5").move("outfeatures", new int[]{2}, -1);
    model.func("dnn5").move("activation", new int[]{2}, -1);
    model.func("dnn5").move("layerDescription", new int[]{2}, -1);
    model.func("dnn5").setIndex("outfeatures", "48", 1);
    model.func("dnn5").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn5").set("outfeatures", new int[]{1, 48, 1, 1});
    model.func("dnn5").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn5")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn5").move("layertype", new int[]{3}, -1);
    model.func("dnn5").move("outfeatures", new int[]{3}, -1);
    model.func("dnn5").move("activation", new int[]{3}, -1);
    model.func("dnn5").move("layerDescription", new int[]{3}, -1);
    model.func("dnn5").setIndex("outfeatures", "24", 2);
    model.func("dnn5").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.func("dnn5").set("outfeatures", new int[]{1, 48, 24, 1, 1});
    model.func("dnn5").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn5")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=24, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn5").move("layertype", new int[]{4}, -1);
    model.func("dnn5").move("outfeatures", new int[]{4}, -1);
    model.func("dnn5").move("activation", new int[]{4}, -1);
    model.func("dnn5").move("layerDescription", new int[]{4}, -1);
    model.func("dnn5").setIndex("outfeatures", "12", 3);
    model.func("dnn5").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn5").set("outfeatures", new int[]{1, 48, 24, 12, 1, 1});
    model.func("dnn5").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn5")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=4", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=48, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=24, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=12, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=3, \u6d3b\u5316=tanh", ""});
    model.func("dnn5").move("layertype", new int[]{5}, -1);
    model.func("dnn5").move("outfeatures", new int[]{5}, -1);
    model.func("dnn5").move("activation", new int[]{5}, -1);
    model.func("dnn5").move("layerDescription", new int[]{5}, -1);
    model.func("dnn5").setIndex("outfeatures", "6", 4);
    model.func("dnn5").set("batchsize", 256);
    model.func("dnn5").set("epochs", 5000);
    model.func("dnn5").run();
    model.func().create("dnn6", "DNN");
    model.func("dnn6").set("source", "resultTable");
    model.func("dnn6").set("resultTable", "tbl7");
    model.func("dnn6").setEntry("columnType", "col3", "value");
    model.func("dnn6").setEntry("columnType", "col4", "none");
    model.func("dnn6").setEntry("columnType", "col5", "none");
    model.func("dnn6").setEntry("funcs", "col3", "dnn6_por");
    model.func("dnn6").setEntry("funcs", "col6", "dnn6_kxx");
    model.func("dnn6").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn6").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn6").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn6")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=2", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=2, \u6d3b\u5316=tanh", ""});
    model.func("dnn6").move("layertype", new int[]{2}, -1);
    model.func("dnn6").move("outfeatures", new int[]{2}, -1);
    model.func("dnn6").move("activation", new int[]{2}, -1);
    model.func("dnn6").move("layerDescription", new int[]{2}, -1);
    model.func("dnn6").setIndex("outfeatures", "32", 1);
    model.func("dnn6").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn6").set("outfeatures", new int[]{1, 32, 1, 1});
    model.func("dnn6").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn6")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=2", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=32, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=2, \u6d3b\u5316=tanh", ""});
    model.func("dnn6").move("layertype", new int[]{3}, -1);
    model.func("dnn6").move("outfeatures", new int[]{3}, -1);
    model.func("dnn6").move("activation", new int[]{3}, -1);
    model.func("dnn6").move("layerDescription", new int[]{3}, -1);
    model.func("dnn6").setIndex("outfeatures", "16", 2);
    model.func("dnn6").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.func("dnn6").set("outfeatures", new int[]{1, 32, 16, 1, 1});
    model.func("dnn6").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn6")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=2", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=32, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=16, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=2, \u6d3b\u5316=tanh", ""});
    model.func("dnn6").move("layertype", new int[]{4}, -1);
    model.func("dnn6").move("outfeatures", new int[]{4}, -1);
    model.func("dnn6").move("activation", new int[]{4}, -1);
    model.func("dnn6").move("layerDescription", new int[]{4}, -1);
    model.func("dnn6").setIndex("outfeatures", "8", 3);
    model.func("dnn6").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.func("dnn6").set("outfeatures", new int[]{1, 32, 16, 8, 1, 1});
    model.func("dnn6").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.func("dnn6")
         .set("layerDescription", new String[]{"\u8f93\u5165, \u8f93\u5165\u7279\u5f81=2", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=32, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=16, \u6d3b\u5316=tanh", "\u9690\u85cf, \u8f93\u51fa\u7279\u5f81=8, \u6d3b\u5316=tanh", "\u8f93\u51fa, \u8f93\u51fa\u7279\u5f81=2, \u6d3b\u5316=tanh", ""});
    model.func("dnn6").move("layertype", new int[]{5}, -1);
    model.func("dnn6").move("outfeatures", new int[]{5}, -1);
    model.func("dnn6").move("activation", new int[]{5}, -1);
    model.func("dnn6").move("layerDescription", new int[]{5}, -1);
    model.func("dnn6").setIndex("outfeatures", "4", 4);
    model.func("dnn6").set("batchsize", 256);
    model.func("dnn6").set("epochs", 5000);
    model.func("dnn6").run();

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "d1_1", 0);
    model.result().numerical("gev2").setIndex("expr", "wm", 1);
    model.result().numerical("gev2").setIndex("expr", "dm", 2);
    model.result().numerical("gev2").setIndex("expr", "hm", 3);
    model.result().numerical("gev2").setIndex("expr", "epsilon_p1", 4);
    model.result().numerical("gev2").setIndex("expr", "(dnn1_kxx(dm, ratio_wmdm, ratio_hmdm, scale_d1))^4", 5);
    model.result().numerical("gev2").setIndex("descr", "DNN1, Permeability, xx-component", 5);
    model.result().numerical("gev2").setIndex("expr", "(dnn1_kyy(dm, ratio_wmdm, ratio_hmdm, scale_d1))^4", 6);
    model.result().numerical("gev2").setIndex("descr", "DNN1, Permeability, yy-component", 6);
    model.result().numerical("gev2").setIndex("expr", "(dnn1_kzz(dm, ratio_wmdm, ratio_hmdm, scale_d1))^4", 7);
    model.result().numerical("gev2").setIndex("descr", "DNN1, Permeability, zz-component", 7);
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl8");
    model.result().numerical("gev2").setResult();
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").setIndex("expr", "d1_2", 0);
    model.result().numerical("gev3").setIndex("expr", "ratio_d1_2", 1);
    model.result().numerical("gev3").setIndex("expr", "wm", 2);
    model.result().numerical("gev3").setIndex("expr", "dm", 3);
    model.result().numerical("gev3").setIndex("expr", "hm", 4);
    model.result().numerical("gev3").setIndex("expr", "epsilon_p2", 5);
    model.result().numerical("gev3").setIndex("descr", "Porosity", 5);
    model.result().numerical("gev3")
         .setIndex("expr", "(dnn2_kxx(dm, ratio_wmdm, ratio_hmdm, scale_d1, ratio_d1_2))^4", 6);
    model.result().numerical("gev3").setIndex("descr", "DNN2, Permeability, xx-component", 6);
    model.result().numerical("gev3")
         .setIndex("expr", "(dnn2_kyy(dm, ratio_wmdm, ratio_hmdm, scale_d1, ratio_d1_2))^4", 7);
    model.result().numerical("gev3").setIndex("descr", "DNN2, Permeability, yy-component", 7);
    model.result().numerical("gev3")
         .setIndex("expr", "(dnn2_kzz(dm, ratio_wmdm, ratio_hmdm, scale_d1, ratio_d1_2))^4", 8);
    model.result().numerical("gev3").setIndex("descr", "DNN2, Permeability, zz-component", 8);
    model.result().table().create("tbl9", "Table");
    model.result().table("tbl9").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl9");
    model.result().numerical("gev3").setResult();
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").setIndex("expr", "d1_3", 0);
    model.result().numerical("gev4").setIndex("expr", "ratio_d1_3", 1);
    model.result().numerical("gev4").setIndex("expr", "epsilon_p3", 5);
    model.result().numerical("gev4")
         .setIndex("expr", "(dnn3_kxx(dm, ratio_wmdm, ratio_hmdm, scale_d1, ratio_d1_3))^4", 6);
    model.result().numerical("gev4").setIndex("descr", "DNN3, Permeability, xx-component", 6);
    model.result().numerical("gev4")
         .setIndex("expr", "(dnn3_kyy(dm, ratio_wmdm, ratio_hmdm, scale_d1, ratio_d1_3))^4", 7);
    model.result().numerical("gev4").setIndex("descr", "DNN3, Permeability, yy-component", 7);
    model.result().numerical("gev4")
         .setIndex("expr", "(dnn3_kzz(dm, ratio_wmdm, ratio_hmdm, scale_d1, ratio_d1_3))^4", 8);
    model.result().numerical("gev4").setIndex("descr", "DNN3, Permeability, zz-component", 8);
    model.result().table().create("tbl10", "Table");
    model.result().table("tbl10").comments("\u5168\u5c40\u8ba1\u7b97 4");
    model.result().numerical("gev4").set("table", "tbl10");
    model.result().numerical("gev4").setResult();
    model.result().numerical().duplicate("gev5", "gev2");
    model.result().numerical().duplicate("gev6", "gev3");
    model.result().numerical("gev5").setIndex("expr", "d1_4", 0);
    model.result().numerical("gev5").setIndex("expr", "epsilon_p4", 4);
    model.result().numerical("gev5").setIndex("expr", "(dnn4_kxx(dm, ratio_hmdm, scale_d1))^4", 5);
    model.result().numerical("gev5").setIndex("descr", "DNN4, Permeability, xx-component", 5);
    model.result().numerical("gev5").setIndex("expr", "(dnn4_kyy(dm, ratio_hmdm, scale_d1))^4", 6);
    model.result().numerical("gev5").setIndex("descr", "DNN4, Permeability, yy-component", 6);
    model.result().numerical("gev5").setIndex("expr", "(dnn4_kzz(dm, ratio_hmdm, scale_d1))^4", 7);
    model.result().numerical("gev5").setIndex("descr", "DNN4, Permeability, zz-component", 7);
    model.result().table().create("tbl11", "Table");
    model.result().table("tbl11").comments("\u5168\u5c40\u8ba1\u7b97 5");
    model.result().numerical("gev5").set("table", "tbl11");
    model.result().numerical("gev5").setResult();
    model.result().numerical("gev6").setIndex("expr", "d1_5", 0);
    model.result().numerical("gev6").setIndex("expr", "ratio_d1_5", 1);
    model.result().numerical("gev6").setIndex("expr", "epsilon_p5", 5);
    model.result().numerical("gev6").setIndex("expr", "(dnn5_kxx(dm, ratio_hmdm, scale_d1, ratio_d1_5))^4", 6);
    model.result().numerical("gev6").setIndex("descr", "DNN5, Permeability, xx-component", 6);
    model.result().numerical("gev6").setIndex("expr", "(dnn5_kyy(dm, ratio_hmdm, scale_d1, ratio_d1_5))^4", 7);
    model.result().numerical("gev6").setIndex("descr", "DNN5, Permeability, yy-component", 7);
    model.result().numerical("gev6").setIndex("expr", "(dnn5_kzz(dm, ratio_hmdm, scale_d1, ratio_d1_5))^4", 8);
    model.result().numerical("gev6").setIndex("descr", "DNN5, Permeability, zz-component", 8);
    model.result().table().create("tbl12", "Table");
    model.result().table("tbl12").comments("\u5168\u5c40\u8ba1\u7b97 6");
    model.result().numerical("gev6").set("table", "tbl12");
    model.result().numerical("gev6").setResult();
    model.result().numerical().create("gev7", "EvalGlobal");
    model.result().numerical("gev7").setIndex("expr", "d1_6", 0);
    model.result().numerical("gev7").setIndex("expr", "L", 1);
    model.result().numerical("gev7").setIndex("expr", "dnn6_por(L, ratio_dL)", 2);
    model.result().numerical("gev7").setIndex("descr", "DNN 6, Porosity", 2);
    model.result().numerical("gev7").setIndex("expr", "(dnn6_kxx(L, ratio_dL))^4", 3);
    model.result().numerical("gev7").setIndex("descr", "DNN 6, Permeability", 3);
    model.result().table().create("tbl13", "Table");
    model.result().table("tbl13").comments("\u5168\u5c40\u8ba1\u7b97 7");
    model.result().numerical("gev7").set("table", "tbl13");
    model.result().numerical("gev7").setResult();
    model.result("pg1").run();

    model.title("\u5747\u8d28\u591a\u5b54\u6750\u6599\u5c5e\u6027");

    model
         .description("\u591a\u5b54\u6750\u6599\u901a\u5e38\u4ee5\u5468\u671f\u6027\u6392\u5217\u7684\u5fae\u89c2\u7ed3\u6784\u6765\u8868\u793a\u3002\u4e3a\u4e86\u8ba1\u7b97\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\uff0c\u9700\u8981\u4f7f\u7528\u5b8f\u89c2\u5c3a\u5ea6\u7684\u6e17\u900f\u7387\u548c\u5b54\u9699\u7387\uff0c\u800c\u8fd9\u4e9b\u53c2\u6570\u80fd\u591f\u6839\u636e\u5fae\u89c2\u5c3a\u5ea6\u57fa\u672c\u5355\u5143\u7684\u8815\u52a8\u6d41\u7ed3\u679c\u8ba1\u7b97\u5f97\u51fa\u3002\u672c App \u63d0\u4f9b\u5404\u79cd\u5fae\u89c2\u7ed3\u6784\uff0c\u5e76\u652f\u6301\u5bf9\u5176\u5c3a\u5bf8\u7279\u6027\u8fdb\u884c\u8c03\u6574\u3002\u968f\u540e\uff0c\u4fbf\u53ef\u4ee5\u8ba1\u7b97\u5e76\u5bfc\u51fa\u5b54\u9699\u7387\u548c\u6e17\u900f\u7387\u7684\u7b49\u6548\u503c\u3002");

    model.label("homogenized_porous_material_properties.mph");

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"csel24"});
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"adjsel3"});
    model.component("comp1").geom("geom1").feature("difsel2")
         .set("subtract", new String[]{"boxsel5", "boxsel15", "boxsel16"});

    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").selection().named("geom1_difsel2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("data", "surf2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "custom");
    model.result("pg3").feature("surf1")
         .set("customcolor", new double[]{0.6549019813537598, 0.6901960968971252, 0.7568627595901489});
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "view1");
    model.result("pg2").run();
    model.result("pg2").set("view", "view1");
    model.result("pg3").run();
    model.result("pg3").set("view", "view1");

    model.component("comp1").view().create("view8", "geom1");
    model.component("comp1").view("view8").hideObjects().create("hide1");
    model.component("comp1").view("view8").hideObjects("hide1").init(3);
    model.component("comp1").view("view8").hideObjects("hide1").named("comsel1");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///homogenized_porous_material_properties.docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("titleimage", "pg3");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature().create("conf1", "Configuration");
    model.result().report("rpt1").feature("sec1").feature("conf1").set("includeauthor", true);
    model.result().report("rpt1").feature("sec1").feature("conf1").set("includedatecreated", true);
    model.result().report("rpt1").feature("sec1").feature("conf1").set("includeversion", true);
    model.result().report("rpt1").feature("sec1").feature().create("txt1", "Text");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("txt2", "Text");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("geom1").set("view", "view8");
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec2").feature("geom1")
         .set("children", new String[][]{{"if1", "off"}, 
         {"pi1", "off"}, 
         {"arr1", "off"}, 
         {"elseif1", "off"}, 
         {"pi2", "off"}, 
         {"arr2", "off"}, 
         {"elseif2", "off"}, 
         {"pi3", "off"}, 
         {"arr3", "off"}, 
         {"elseif3", "off"}, 
         {"pi4", "off"}, 
         {"arr4", "off"}, 
         {"elseif4", "off"}, 
         {"pi5", "off"}, 
         {"arr5", "off"}, 
         {"elseif5", "off"}, 
         {"pi6", "off"}, 
         {"blk1", "off"}, 
         {"adjsel1", "off"}, 
         {"dif1", "off"}, 
         {"boxsel1", "off"}, 
         {"boxsel2", "off"}, 
         {"difsel1", "off"}, 
         {"boxsel3", "off"}, 
         {"boxsel4", "off"}, 
         {"endif1", "off"}, 
         {"boxsel5", "off"}, 
         {"boxsel6", "off"}, 
         {"boxsel7", "off"}, 
         {"boxsel8", "off"}, 
         {"boxsel9", "off"}, 
         {"boxsel10", "off"}, 
         {"boxsel11", "off"}, 
         {"boxsel12", "off"}, 
         {"boxsel13", "off"}, 
         {"boxsel14", "off"}, 
         {"comsel1", "off"}, 
         {"adjsel2", "off"}, 
         {"boxsel15", "off"}, 
         {"boxsel16", "off"}, 
         {"unisel1", "off"}, 
         {"adjsel3", "off"}, 
         {"difsel2", "off"}, 
         {"fin", "off"}});
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature().duplicate("param2", "param1");
    model.result().report("rpt1").feature("sec2").feature("param2").set("noderef", "par2");
    model.result().report("rpt1").feature("sec2").feature().duplicate("param3", "param1");
    model.result().report("rpt1").feature("sec2").feature().duplicate("param4", "param2");
    model.result().report("rpt1").feature("sec2").feature("param3").set("noderef", "par3");
    model.result().report("rpt1").feature("sec2").feature("param4").set("noderef", "par4");
    model.result().report("rpt1").feature("sec2").feature().duplicate("param5", "param2");
    model.result().report("rpt1").feature("sec2").feature().duplicate("param6", "param3");
    model.result().report("rpt1").feature("sec2").feature().duplicate("param7", "param4");
    model.result().report("rpt1").feature("sec2").feature("param5").set("noderef", "par5");
    model.result().report("rpt1").feature("sec2").feature("param6").set("noderef", "par6");
    model.result().report("rpt1").feature("sec2").feature("param7").set("noderef", "par7");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl2", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl2").set("noderef", "tbl8");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl3", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl3").set("noderef", "tbl9");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl4", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl4").set("noderef", "tbl10");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl5", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl5").set("noderef", "tbl11");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl6", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl6").set("noderef", "tbl12");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("mtbl7", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("mtbl7").set("noderef", "tbl13");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg1").set("imagesize", "large");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg2").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg2").set("imagesize", "large");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg3").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg3").set("imagesize", "large");

    model.title("\u5747\u8d28\u591a\u5b54\u6750\u6599\u5c5e\u6027");

    model
         .description("\u591a\u5b54\u6750\u6599\u901a\u5e38\u4ee5\u5468\u671f\u6027\u6392\u5217\u7684\u5fae\u89c2\u7ed3\u6784\u6765\u8868\u793a\u3002\u4e3a\u4e86\u8ba1\u7b97\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\uff0c\u9700\u8981\u4f7f\u7528\u5b8f\u89c2\u5c3a\u5ea6\u7684\u6e17\u900f\u7387\u548c\u5b54\u9699\u7387\uff0c\u800c\u8fd9\u4e9b\u53c2\u6570\u80fd\u591f\u6839\u636e\u5fae\u89c2\u5c3a\u5ea6\u57fa\u672c\u5355\u5143\u7684\u8815\u52a8\u6d41\u7ed3\u679c\u8ba1\u7b97\u5f97\u51fa\u3002\u672c App \u63d0\u4f9b\u5404\u79cd\u5fae\u89c2\u7ed3\u6784\uff0c\u5e76\u652f\u6301\u5bf9\u5176\u5c3a\u5bf8\u7279\u6027\u8fdb\u884c\u8c03\u6574\u3002\u968f\u540e\uff0c\u4fbf\u53ef\u4ee5\u8ba1\u7b97\u5e76\u5bfc\u51fa\u5b54\u9699\u7387\u548c\u6e17\u900f\u7387\u7684\u7b49\u6548\u503c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("homogenized_porous_material_properties.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
