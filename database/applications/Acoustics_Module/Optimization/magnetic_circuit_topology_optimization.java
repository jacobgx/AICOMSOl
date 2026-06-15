/*
 * magnetic_circuit_topology_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class magnetic_circuit_topology_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1")
         .insertFile("magnetic_circuit_topology_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("B0", "0.4[T]", "\u78c1\u4f53\u5269\u4f59\u78c1\u901a");
    model.param("par2").set("N0", "100", "\u531d\u6570");
    model.param("par2").set("BL_0", "7.5[Wb/m]", "\u76ee\u6807 BL");
    model.param("par2").set("volfrac", "0.4", "\u76ee\u6807\u5bc6\u5ea6");
    model.param("par2").set("mesh_size", "0.4[mm]", "\u8bbe\u8ba1\u7a7a\u95f4\u7684\u7f51\u683c\u5927\u5c0f");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.material("mat1").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.material("mat1").propertyGroup().create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.material("mat1").propertyGroup("EffectiveBHCurve").func().create("BHeff", "Interpolation");
    model.material("mat1").label("Soft Iron (With Losses)");
    model.material("mat1").set("family", "iron");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("BHCurve").label("B-H Curve");
    model.material("mat1").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.material("mat1").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.material("mat1").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.material("mat1").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.material("mat1").propertyGroup("BHCurve").func("BH").set("argunit", new String[]{"A/m"});
    model.material("mat1").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.material("mat1").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.material("mat1").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.material("mat1").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.material("mat1").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.material("mat1").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.material("mat1").propertyGroup("BHCurve").descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.material("mat1").propertyGroup("BHCurve").addInput("magneticfield");
    model.material("mat1").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.material("mat1").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").label("Interpolation 1");
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("extrap", "linear");
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("fununit", new String[]{"T"});
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("argunit", new String[]{"A/m"});
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineprimfun", true);
    model.material("mat1").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.material("mat1").propertyGroup("EffectiveBHCurve").set("normHeff", "BHeff_inv(normBeffin)");
    model.material("mat1").propertyGroup("EffectiveBHCurve").set("Wpmeff", "BHeff_prim(normHeffin)");
    model.material("mat1").propertyGroup("EffectiveBHCurve").descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u901a\u7528\u94c1\u6c27\u4f53");
    model.material("mat2").propertyGroup("def").set("electricconductivity", "");
    model.material("mat2").propertyGroup("def").set("relpermittivity", "");
    model.material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent_flux_density");
    model.material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat2").propertyGroup("RemanentFluxDensity").set("murec", new String[]{"1"});
    model.material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", new String[]{"B0"});

    model.func().create("an1", "Analytic");
    model.func("an1").label("mat1 \u7684\u7b49\u6548 mur\uff0c\u8f6f\u94c1\uff08\u5305\u542b\u635f\u8017\uff09");
    model.func("an1").set("funcname", "mur1");
    model.func("an1")
         .set("expr", "sqrt(Bx^2+By^2+eps[T^2])/mat1.BHCurve.BH_inv(sqrt(Bx^2+By^2+eps[T^2]))/mu0_const");
    model.func("an1").set("args", "Bx, By");
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("argunit", "T", 0);
    model.func("an1").setIndex("argunit", "T", 1);
    model.func("an1").setIndex("plotargs", 2, 0, 2);
    model.func("an1").setIndex("plotargs", 2, 1, 2);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u83b7\u53d6 BL \u56e0\u5b50\u7684\u533a\u57df");
    model.component("comp1").cpl("intop1").set("opname", "int_BL");
    model.component("comp1").cpl("intop1").selection().named("geom1_r5_dom");
    model.component("comp1").cpl("intop1").set("intorder", 30);
    model.component("comp1").cpl("intop1").set("axisym", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5168\u5c40\u53d8\u91cf");
    model.component("comp1").variable("var1").set("BL_integrand", "-mf.Br*N0*2*pi*r/(w_coil*h_coil)");
    model.component("comp1").variable("var1")
         .descr("BL_integrand", "\u83b7\u53d6 BL \u56e0\u5b50\u7684\u88ab\u79ef\u51fd\u6570");
    model.component("comp1").variable("var1")
         .set("coil_location_z", "(z>(dest(z)-h_coil/2))*(z<(dest(z)+h_coil/2))");
    model.component("comp1").variable("var1")
         .descr("coil_location_z", "\u8fa8\u522b\u7ebf\u5708\u4f4d\u7f6e\u7684\u903b\u8f91\u6761\u4ef6");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u70b9\u53d8\u91cf");
    model.component("comp1").variable("var2").selection().geom("geom1", 0);
    model.component("comp1").variable("var2").selection().all();
    model.component("comp1").variable("var2").set("BL_point", "int_BL(BL_integrand*coil_location_z)");
    model.component("comp1").variable("var2").descr("BL_point", "\u70b9\u5904\u7684 BL \u56e0\u5b50");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").label("\u70b9\u63a2\u9488 - \u76ee\u6807 1");
    model.component("comp1").probe("point1").set("probename", "obj_1");
    model.component("comp1").probe("point1").selection().named("geom1_pt2_pnt");
    model.component("comp1").probe("point1").set("expr", "BL_point");
    model.component("comp1").probe().create("point2", "Point");
    model.component("comp1").probe("point2").label("\u70b9\u63a2\u9488 - \u76ee\u6807 2");
    model.component("comp1").probe("point2").set("probename", "obj_2");
    model.component("comp1").probe("point2").selection().named("geom1_arr1_pnt");
    model.component("comp1").probe("point2").set("expr", "(BL_point-BL_0)^2");

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").selection().named("geom1_difsel1");
    model.component("comp1").common("dtopo1").set("simpExponentType", "Custom");
    model.component("comp1").common("dtopo1").set("p_SIMP", "10");
    model.component("comp1").common("dtopo1").set("thetaMinType", "Custom");
    model.component("comp1").common("dtopo1").set("theta_min", "0");
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "volfrac");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u62d3\u6251\u4f18\u5316");
    model.component("comp1").material("matlnk1").selection().named("geom1_difsel1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("\u78c1\u4f53");
    model.component("comp1").material("matlnk2").selection().named("geom1_r4_dom");
    model.component("comp1").material("matlnk2").set("link", "mat2");

    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").label("\u62d3\u6251\u4f18\u5316");
    model.component("comp1").physics("mf").feature("als1").selection().named("geom1_difsel1");
    model.component("comp1").physics("mf").feature("als1").set("mur_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1")
         .set("mur", new String[]{"1+dtopo1.theta_p*(mur1(mf.Br,mf.Bz)-1)", "0", "0", "0", "1+dtopo1.theta_p*(mur1(mf.Br,mf.Bz)-1)", "0", "0", "0", "1+dtopo1.theta_p*(mur1(mf.Br,mf.Bz)-1)"});
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").label("\u78c1\u4f53");
    model.component("comp1").physics("mf").feature("als2").selection().named("geom1_r4_dom");
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als2").set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u9759\u6b62\u4f4d\u7f6e\u4f18\u5316\u540e\u7684\u6700\u5927 BL");
    model.study("std1").create("topo", "TopologyOptimization");
    model.study("std1").feature("topo").set("mmamaxiter", 20);
    model.study("std1").feature("topo").set("optobj", new String[]{"comp1.obj_1"});
    model.study("std1").feature("topo").set("descr", new String[]{"\u70b9\u63a2\u9488 - \u76ee\u6807 1"});
    model.study("std1").feature("topo").set("objectivetype", "maximization");
    model.study("std1").feature("topo").set("objectivescaling", "init");
    model.study("std1").feature("topo").set("constraintExpression", new String[]{"comp1.dtopo1.theta_avg"});
    model.study("std1").feature("topo").setIndex("constraintUbound", "volfrac", 0);
    model.study("std1").feature("topo").setIndex("constraintLbound", "", 0);
    model.study("std1").feature("topo").set("probesel", "none");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("grp1", 1);

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u9608\u503c");

    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt1").set("data", "dset1");
    model.result().dataset("filt1").set("expr", "dtopo1.theta");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").set("smooth", "none");
    model.result().dataset("filt1").set("useder", false);
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("rangecolormin", 0);
    model.result("pg3").feature("surf1").set("rangecolormax", 1);
    model.result("pg4").set("data", "filt1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg1").run();

    model.study("std1").feature("topo").set("plot", true);
    model.study("std1").feature("topo").set("plotgroup", "pg3");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std1").feature("topo").set("probewindow", "");

    model.result().dataset().remove("rev1");
    model.result().dataset("filt1")
         .label("\u8fc7\u6ee4\u5668 - \u9759\u6b62\u4f4d\u7f6e\u4f18\u5316\u540e\u7684\u6700\u5927 BL");
    model.result().dataset("filt1").set("expr", "if(isnan(dtopo1.theta_c),NaN,dtopo1.theta)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "view1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", 2);
    model.result("pg1").run();
    model.result("pg1").run();

    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").label("\u9759\u6b62\u4f4d\u7f6e\u4f18\u5316\u540e\u7684\u6700\u5927 BL \u7ed3\u679c");

    model.result("pg3").run();
    model.result("pg3").set("view", "view1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "if(isnan(dtopo1.theta_c),NaN,dtopo1.theta)");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5e73\u7f13 BL \u4f18\u5316");
    model.study("std2").create("topo", "TopologyOptimization");
    model.study("std2").feature("topo").set("mmamaxiter", 50);
    model.study("std2").feature("topo").set("optobj", new String[]{"comp1.obj_2"});
    model.study("std2").feature("topo").set("descr", new String[]{"\u70b9\u63a2\u9488 - \u76ee\u6807 2"});
    model.study("std2").feature("topo").set("objectivescaling", "init");
    model.study("std2").feature("topo").set("constraintExpression", new String[]{"comp1.dtopo1.theta_avg"});
    model.study("std2").feature("topo").setIndex("constraintUbound", "volfrac", 0);
    model.study("std2").feature("topo").setIndex("constraintLbound", "", 0);
    model.study("std2").feature("topo").set("probesel", "none");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.03);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("solutionparams", "parent");
    model.result("pg5").feature("con1").set("expr", "mf.Psi");
    model.result("pg5").feature("con1").set("titletype", "none");
    model.result("pg5").feature("con1").set("number", 10);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("color", "custom");
    model.result("pg5").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg5").feature("con1").set("resolution", "fine");
    model.result("pg5").feature("con1").set("inheritcolor", false);
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg5").feature("con1").set("inheritplot", "surf1");
    model.result("pg5").feature("con1").feature().create("filt1", "Filter");
    model.result("pg5").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result("pg6").feature().create("con1", "Contour");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("solutionparams", "parent");
    model.result("pg6").feature("con1").set("expr", "mf.Psi");
    model.result("pg6").feature("con1").set("titletype", "none");
    model.result("pg6").feature("con1").set("number", 10);
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("coloring", "uniform");
    model.result("pg6").feature("con1").set("colorlegend", false);
    model.result("pg6").feature("con1").set("color", "custom");
    model.result("pg6").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg6").feature("con1").set("resolution", "fine");
    model.result("pg6").feature("con1").set("inheritcolor", false);
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("data", "parent");
    model.result("pg6").feature("con1").set("inheritplot", "vol1");
    model.result("pg6").feature("con1").feature().create("filt1", "Filter");
    model.result("pg6").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg6").feature("con1").feature("filt1").set("shownodespec", "on");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg6");
    model.nodeGroup().move("grp2", 2);

    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 1");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u9608\u503c 1");

    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");

    model.result().dataset().create("filt2", "Filter");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt2").set("data", "dset3");
    model.result().dataset("filt2").set("expr", "dtopo1.theta");
    model.result().dataset("filt2").set("lowerexpr", "0.5");
    model.result().dataset("filt2").set("smooth", "none");
    model.result().dataset("filt2").set("useder", false);
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("rangecolormin", 0);
    model.result("pg7").feature("surf1").set("rangecolormax", 1);
    model.result("pg8").set("data", "filt2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "1");
    model.result("pg8").feature("surf1").set("coloring", "uniform");
    model.result("pg8").feature("surf1").set("color", "gray");
    model.result("pg8").feature("surf1").set("titletype", "none");
    model.result("pg5").run();

    model.study("std2").feature("topo").set("plot", true);
    model.study("std2").feature("topo").set("plotgroup", "pg7");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg5").run();

    model.study("std2").feature("topo").set("probewindow", "");

    model.result().dataset().remove("rev1");
    model.result().dataset("filt2").label("\u8fc7\u6ee4\u5668 - \u5e73\u7f13 BL \u4f18\u5316");
    model.result().dataset("filt2").set("expr", "if(isnan(dtopo1.theta_c),NaN,dtopo1.theta)");
    model.result().dataset().duplicate("filt3", "filt2");
    model.result().dataset("filt3").label("\u8fc7\u6ee4\u5668 - \u521d\u59cb\u51e0\u4f55");
    model.result().dataset("filt3").set("expr", "1");
    model.result("pg8").run();
    model.result("pg8").set("view", "view1");
    model.result("pg8").run();
    model.result("pg5").run();
    model.result("pg5").set("view", "view1");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("rangecolormax", 2);
    model.result("pg5").run();
    model.result("pg5").run();

    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").label("\u5e73\u7f13 BL \u4f18\u5316\u7ed3\u679c");

    model.result("pg7").run();
    model.result("pg7").set("view", "view1");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "if(isnan(dtopo1.theta_c),NaN,dtopo1.theta)");
    model.result("pg7").feature("surf1").set("descractive", true);
    model.result("pg7").feature("surf1").set("descr", "\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50");
    model.result("pg7").run();
    model.result().dataset("filt2").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.mesh("mpart1").run("imp1");

    model.result().dataset("filt3").createMeshPart("mcomp2", "mgeom2", "mpart2", "imp1");

    model.mesh("mpart2").run("imp1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "mesh");
    model.component("comp2").geom("geom2").feature("imp1").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").create("imp2", "Import");
    model.component("comp2").geom("geom2").feature("imp2").set("type", "mesh");
    model.component("comp2").geom("geom2").feature("imp2").set("mesh", "mpart2");
    model.component("comp2").geom("geom2").run("imp2");

    model.component("comp2").cpl().create("intop2", "Integration");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").label("\u83b7\u53d6 BL \u56e0\u5b50\u7684\u533a\u57df 2");
    model.component("comp2").cpl("intop2").set("opname", "int_BL2");
    model.component("comp2").cpl("intop2").selection().named("geom2_imp2_mpart2_imp1_geom1_r5_dom");
    model.component("comp2").cpl("intop2").set("intorder", 30);
    model.component("comp2").cpl("intop2").set("axisym", false);

    model.component("comp2").variable().create("var3");
    model.component("comp2").variable("var3").label("\u5168\u5c40\u53d8\u91cf 2");
    model.component("comp2").variable("var3")
         .set("BL_integrand", "-mf2.Br*N0*2*pi*r/(w_coil*h_coil)", "\u83b7\u53d6 BL \u56e0\u5b50\u7684\u88ab\u79ef\u51fd\u6570");
    model.component("comp2").variable("var3")
         .set("coil_location_z", "(z>(dest(z)-h_coil/2))*(z<(dest(z)+h_coil/2))", "\u8fa8\u522b\u7ebf\u5708\u4f4d\u7f6e\u7684\u903b\u8f91\u6761\u4ef6");

    model.component("comp2").material().create("matlnk3", "Link");
    model.component("comp2").material("matlnk3").label("\u94c1");
    model.component("comp2").material("matlnk3").selection().named("geom2_imp1_mpart1_imp1_geom1_difsel1");
    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").label("\u78c1\u4f53");
    model.component("comp2").material("matlnk4").selection().named("geom2_imp2_mpart2_imp1_geom1_r4_dom");
    model.component("comp2").material("matlnk4").set("link", "mat2");

    model.component("comp2").physics().create("mf2", "InductionCurrents", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/mf2", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mf2", false);

    model.component("comp2").physics("mf2").create("als1", "AmperesLawSolid", 2);
    model.component("comp2").physics("mf2").feature("als1").label("\u94c1");
    model.component("comp2").physics("mf2").feature("als1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_difsel1");
    model.component("comp2").physics("mf2").feature("als1").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp2").physics("mf2").create("als2", "AmperesLawSolid", 2);
    model.component("comp2").physics("mf2").feature("als2").label("\u78c1\u4f53");
    model.component("comp2").physics("mf2").feature("als2").selection().named("geom2_imp2_mpart2_imp1_geom1_r4_dom");
    model.component("comp2").physics("mf2").feature("als2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp2").physics("mf2").feature("als2").set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("map1").selection().named("geom2_imp2_mpart2_imp1_geom1_unisel1");
    model.component("comp2").mesh("mesh2").feature("map1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("map1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("map1").feature("size1").set("hmax", "mesh_size");
    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_difsel1");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std3").feature("stat").setSolveFor("/physics/mf2", true);
    model.study("std3").feature("stat").set("probesel", "none");
    model.study("std3").label("\u7814\u7a76 3 - \u5e73\u7f13 BL \u9a8c\u8bc1");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u78c1\u901a\u5bc6\u5ea6 (mf2)");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").set("dataisaxisym", "off");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").feature().create("str1", "Streamline");
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("solutionparams", "parent");
    model.result("pg9").feature("str1").set("titletype", "none");
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("udist", 0.03);
    model.result("pg9").feature("str1").set("maxlen", 0.4);
    model.result("pg9").feature("str1").set("maxsteps", 5000);
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("inheritcolor", false);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("data", "parent");
    model.result("pg9").feature("str1").selection().geom("geom2", 1);
    model.result("pg9").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    model.result("pg9").feature("str1").set("inheritplot", "surf1");
    model.result("pg9").feature("str1").feature().create("col1", "Color");
    model.result("pg9").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg9").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg9").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg9").feature("str1").feature().create("filt1", "Filter");
    model.result("pg9").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg9").feature().create("con1", "Contour");
    model.result("pg9").feature("con1").set("showsolutionparams", "on");
    model.result("pg9").feature("con1").set("solutionparams", "parent");
    model.result("pg9").feature("con1").set("expr", "mf2.Psi");
    model.result("pg9").feature("con1").set("titletype", "none");
    model.result("pg9").feature("con1").set("number", 10);
    model.result("pg9").feature("con1").set("levelrounding", false);
    model.result("pg9").feature("con1").set("coloring", "uniform");
    model.result("pg9").feature("con1").set("colorlegend", false);
    model.result("pg9").feature("con1").set("color", "custom");
    model.result("pg9").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg9").feature("con1").set("resolution", "fine");
    model.result("pg9").feature("con1").set("inheritcolor", false);
    model.result("pg9").feature("con1").set("showsolutionparams", "on");
    model.result("pg9").feature("con1").set("data", "parent");
    model.result("pg9").feature("con1").set("inheritplot", "surf1");
    model.result("pg9").feature("con1").feature().create("filt1", "Filter");
    model.result("pg9").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset5");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf2)");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature().create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("solutionparams", "parent");
    model.result("pg10").feature("vol1").set("colortable", "Prism");
    model.result("pg10").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg10").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("data", "parent");
    model.result("pg10").feature().create("con1", "Contour");
    model.result("pg10").feature("con1").set("showsolutionparams", "on");
    model.result("pg10").feature("con1").set("solutionparams", "parent");
    model.result("pg10").feature("con1").set("expr", "mf2.Psi");
    model.result("pg10").feature("con1").set("titletype", "none");
    model.result("pg10").feature("con1").set("number", 10);
    model.result("pg10").feature("con1").set("levelrounding", false);
    model.result("pg10").feature("con1").set("coloring", "uniform");
    model.result("pg10").feature("con1").set("colorlegend", false);
    model.result("pg10").feature("con1").set("color", "custom");
    model.result("pg10").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg10").feature("con1").set("resolution", "fine");
    model.result("pg10").feature("con1").set("inheritcolor", false);
    model.result("pg10").feature("con1").set("showsolutionparams", "on");
    model.result("pg10").feature("con1").set("data", "parent");
    model.result("pg10").feature("con1").set("inheritplot", "vol1");
    model.result("pg10").feature("con1").feature().create("filt1", "Filter");
    model.result("pg10").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg10").feature("con1").feature("filt1").set("shownodespec", "on");

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").label("\u62d3\u6251\u4f18\u5316");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter("plotgroup", "pg10");
    model.nodeGroup().move("grp3", 3);

    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u8f93\u51fa\u6750\u6599\u4f53\u79ef\u56e0\u5b50 2");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u9608\u503c 2");

    model.nodeGroup("grp3").add("plotgroup", "pg11");
    model.nodeGroup("grp3").add("plotgroup", "pg12");

    model.result().dataset().create("filt4", "Filter");
    model.result().dataset("filt4").label("\u8fc7\u6ee4\u5668");
    model.result().dataset("filt4").set("data", "dset4");
    model.result().dataset("filt4").set("expr", "dtopo1.theta");
    model.result().dataset("filt4").set("lowerexpr", "0.5");
    model.result().dataset("filt4").set("smooth", "none");
    model.result().dataset("filt4").set("useder", false);
    model.result("pg11").set("data", "dset4");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "dtopo1.theta");
    model.result("pg11").feature("surf1").set("rangecoloractive", true);
    model.result("pg11").feature("surf1").set("colortabletrans", "none");
    model.result("pg11").feature("surf1").set("rangecolormin", 0);
    model.result("pg11").feature("surf1").set("rangecolormax", 1);
    model.result("pg12").set("data", "filt4");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "1");
    model.result("pg12").feature("surf1").set("coloring", "uniform");
    model.result("pg12").feature("surf1").set("color", "gray");
    model.result("pg12").feature("surf1").set("titletype", "none");
    model.result("pg9").run();
    model.result("pg9").set("view", "view1");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").feature("surf1").set("rangecolormin", 0);
    model.result("pg9").feature("surf1").set("rangecolormax", 2);
    model.result("pg9").run();
    model.result("pg9").run();

    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result("pg11").run();
    model.result().remove("pg11");
    model.result().remove("pg12");
    model.result("pg9").run();

    model.nodeGroup("grp3").label("\u5e73\u7f13 BL \u9a8c\u8bc1");

    model.result().dataset().remove("dset4");
    model.result().dataset().remove("rev1");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u4f20\u7edf\u8bbe\u8ba1 BL \u66f2\u7ebf");
    model.result().table("tbl3").importData("magnetic_circuit_topology_optimization_traditional_bl.txt");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("BL \u56e0\u5b50");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u97f3\u5708\u504f\u79fb\u91cf (mm)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "BL \u56e0\u5b50 (Wb/m)");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("legendpos", "lowermiddle");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").selection().named("geom1_ls1_bnd");
    model.result("pg10").feature("lngr1").set("expr", "int_BL(BL_integrand*coil_location_z)");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "(z-z_coil)");
    model.result("pg10").feature("lngr1").set("xdatadescractive", true);
    model.result("pg10").feature("lngr1").set("xdatadescr", "\u7ebf\u5708\u504f\u79fb\u91cf");
    model.result("pg10").feature("lngr1").set("linewidth", 2);
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").feature("lngr1").set("autosolution", false);
    model.result("pg10").feature("lngr1")
         .set("legendprefix", "\u9759\u6b62\u4f4d\u7f6e\u4f18\u5316\u540e\u7684\u6700\u5927 BL");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("lngr2", "lngr1");
    model.result("pg10").run();
    model.result("pg10").feature("lngr2").set("data", "dset3");
    model.result("pg10").feature("lngr2").set("legendprefix", "\u5e73\u7f13 BL \u4f18\u5316");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("lngr3", "lngr2");
    model.result("pg10").run();
    model.result("pg10").feature("lngr3").set("data", "dset5");
    model.result("pg10").feature("lngr3").selection().named("geom2_imp2_mpart2_imp1_geom1_ls1_bnd");
    model.result("pg10").feature("lngr3").set("expr", "int_BL2(BL_integrand*coil_location_z)");
    model.result("pg10").feature("lngr3").set("legendprefix", "\u5e73\u7f13 BL \u9a8c\u8bc1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("ann1", "Annotation");
    model.result("pg10").feature("ann1").set("showpoint", false);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("tblp1", "Table");
    model.result("pg10").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg10").feature("tblp1").set("linewidth", "preference");
    model.result("pg10").feature("tblp1").set("table", "tbl3");
    model.result("pg10").run();
    model.result("pg10").feature("tblp1").set("legend", true);
    model.result("pg10").feature("tblp1").set("legendmethod", "manual");
    model.result("pg10").feature("tblp1").setIndex("legends", "\u4f20\u7edf\u8bbe\u8ba1", 0);
    model.result("pg10").feature("tblp1").set("linewidth", 2);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").selection().named("geom1_pt2_pnt");
    model.result("pg10").feature("ptgr1").set("expr", "int_BL(BL_integrand*coil_location_z)");
    model.result("pg10").feature("ptgr1").set("xdata", "expr");
    model.result("pg10").feature("ptgr1").set("xdataexpr", "(z-z_coil)");
    model.result("pg10").feature("ptgr1").set("linestyle", "none");
    model.result("pg10").feature("ptgr1").set("linecolor", "cyclereset");
    model.result("pg10").feature("ptgr1").set("linewidth", 5);
    model.result("pg10").feature("ptgr1").set("linemarker", "diamond");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("data", "dset3");
    model.result("pg10").feature("ptgr2").selection().named("geom1_arr1_pnt");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("linecolor", "custom");
    model.result("pg10").feature("ptgr2")
         .set("customlinecolor", new double[]{0.3333333432674408, 0.7843137383460999, 0.40392157435417175});
    model.result("pg10").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u94c1\u4f53\u79ef");
    model.result().evaluationGroup("eg1").set("data", "none");
    model.result().evaluationGroup("eg1").create("meas1", "MeasureSurface");
    model.result().evaluationGroup("eg1").feature("meas1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("meas1").set("data", "filt1");
    model.result().evaluationGroup("eg1").feature("meas1").set("unit", "cm^3");
    model.result().evaluationGroup("eg1").feature("meas1").set("descractive", true);
    model.result().evaluationGroup("eg1").feature("meas1").set("descr", "\u6700\u5927 BL \u4f18\u5316");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").create("meas2", "MeasureSurface");
    model.result().evaluationGroup("eg1").feature("meas2").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("meas2").set("data", "filt2");
    model.result().evaluationGroup("eg1").feature("meas2").set("unit", "cm^3");
    model.result().evaluationGroup("eg1").feature("meas2").set("descractive", true);
    model.result().evaluationGroup("eg1").feature("meas2").set("descr", "\u5e73 BL \u4f18\u5316");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").create("meas3", "MeasureSurface");
    model.result().evaluationGroup("eg1").feature("meas3").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("meas3").set("data", "dset5");
    model.result().evaluationGroup("eg1").feature("meas3").selection().set(3, 10);
    model.result().evaluationGroup("eg1").feature("meas3").set("unit", "cm^3");
    model.result().evaluationGroup("eg1").feature("meas3").set("descractive", true);
    model.result().evaluationGroup("eg1").feature("meas3").set("descr", "\u5e73 BL \u9a8c\u8bc1");
    model.result().evaluationGroup("eg1").run();
    model.result("pg8").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg9").run();

    model.title("\u78c1\u8def\u7684\u62d3\u6251\u4f18\u5316");

    model
         .description("\u672c\u6a21\u578b\u63d0\u4f9b\u4e24\u4e2a\u6709\u5173\u626c\u58f0\u5668\u9a71\u52a8\u5668\u78c1\u8def\u62d3\u6251\u4f18\u5316\u7684\u793a\u4f8b\u3002\u7b2c\u4e00\u4e2a\u4f18\u5316\u65e8\u5728\u786e\u5b9a\u975e\u7ebf\u6027\u94c1\u6781\u7247\u548c\u9876\u677f\u7684\u8bbe\u8ba1\uff0c\u4ee5\u6700\u5927\u5316\u5728\u9759\u6b62\u4f4d\u7f6e\uff08\u5c0f\u4f4d\u79fb\uff09\u4e0b\u7684 BL \u56e0\u5b50\uff0c\u540c\u65f6\u9650\u5236\u94c1\u7684\u4f7f\u7528\u91cf\u3002\u7b2c\u4e8c\u4e2a\u4f18\u5316\u7528\u4e8e\u786e\u5b9a\u5177\u6709\u5e73\u5766 BL \u66f2\u7ebf\u7684\u78c1\u8def\u8bbe\u8ba1\uff0c\u540c\u65f6\u9650\u5236\u94c1\u7684\u4f7f\u7528\u91cf\u3002\u8fd9\u6837\u7684\u8bbe\u8ba1\u53ef\u4ee5\u51cf\u5c11\u626c\u58f0\u5668\u7eb8\u76c6\u5728\u5927\u4f4d\u79fb\u65f6\u7684\u975e\u7ebf\u6027\u3002\u7b2c\u4e8c\u4e2a\u4f18\u5316\u751f\u6210\u7684\u8bbe\u8ba1\u5df2\u5728\u65b0\u7684\u7814\u7a76\u4e2d\u5f97\u5230\u9a8c\u8bc1\uff0c\u8868\u73b0\u51fa\u4e0e\u62d3\u6251\u7ed3\u679c\u826f\u597d\u7684\u4e00\u81f4\u6027\u3002\u4e0e\u4f20\u7edf\u8bbe\u8ba1\u76f8\u6bd4\uff0c\u8fd9\u4e24\u79cd\u8bbe\u8ba1\u90fd\u663e\u793a\u51fa\u6027\u80fd\u7684\u63d0\u5347\uff0c\u540c\u65f6\u9700\u8981\u7684\u94c1\u6750\u6599\u4e5f\u6709\u6240\u51cf\u5c11\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("mpart1").clearMesh();
    model.mesh("mpart2").clearMesh();
    model.component("comp2").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("magnetic_circuit_topology_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
