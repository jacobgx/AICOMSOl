/*
 * frozen_inclusion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:12 by COMSOL 6.3.0.290. */
public class frozen_inclusion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_initial_w", "5[degC]", "\u89e3\u51bb\u57df\u7684\u521d\u59cb\u6e29\u5ea6");
    model.param().set("T_initial_i", "-5[degC]", "\u5939\u6742\u7269\u7684\u521d\u59cb\u6e29\u5ea6");
    model.param().set("lambda_w", "0.6[W/(m*K)]", "\u6c34\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("lambda_i", "2.14[W/(m*K)]", "\u51b0\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("lambda_s", "9[W/(m*K)]", "\u56fa\u4f53\u57fa\u8d28\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cw", "4182[J/(kg*K)]", "\u6c34\u7684\u70ed\u5bb9");
    model.param().set("Ci", "2060[J/(kg*K)]", "\u51b0\u7684\u70ed\u5bb9");
    model.param().set("Cs", "835[J/(kg*K)]", "\u56fa\u4f53\u57fa\u8d28\u7684\u70ed\u5bb9");
    model.param().set("rho_w", "1000[kg/m^3]", "\u6c34\u5bc6\u5ea6");
    model.param().set("rho_i", "920[kg/m^3]", "\u51b0\u7684\u5bc6\u5ea6");
    model.param().set("rho_s", "2650[kg/m^3]", "\u56fa\u4f53\u57fa\u8d28\u7684\u5bc6\u5ea6");
    model.param().set("L", "334[kJ/kg]", "\u6f5c\u70ed");
    model.param().set("epsilon_p", "0.37", "\u5b54\u9699\u7387");
    model.param().set("beta", "1e-8[1/Pa]", "\u6709\u6548\u538b\u7f29\u7cfb\u6570");
    model.param().set("Sw_res", "0.05", "\u6b8b\u4f59\u9971\u548c\u5ea6 Sw(T)");
    model.param().set("mu_w", "1.793e-3[Pa*s]", "\u6c34\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("k_int", "1.3e-10[m^2]", "\u56fa\u6709\u6e17\u900f\u7387");
    model.param().set("Omega", "50", "\u963b\u6297\u56e0\u5b50");
    model.param().set("T_pc", "-0.5[degC]", "\u76f8\u53d8\u6e29\u5ea6");
    model.param().set("delta_H_dLx", "3[%]", "\u6c34\u5934\u68af\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{3, 0.5});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.333", "0.333/2"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"1", "0.5-0.333/4"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u56fa\u4f53\u57fa\u8d28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6c34");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u51b0");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().all();
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").set("link", "mat2");
    model.component("comp1").material("pmat1").feature("solid1").set("link", "mat1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-epsilon_p");
    model.component("comp1").material("pmat1").feature().create("imfluid1", "ImFluid", "comp1");
    model.component("comp1").material("pmat1").feature("imfluid1").set("link", "mat2");
    model.component("comp1").material("pmat1").feature("imfluid1").set("vfrac", "epsilon_p*Sw_res");

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_initial_w");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").feature("porous1").create("imf1", "ImmobileFluidPorousMaterial", 2);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "root.comp1.dl.pA");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .create("phc1", "PhaseChangeMaterial", 2);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("PhaseTransitionFunction12", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("alpha12", "f_phtr(T)");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1").set("L_pc12", "L");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("MaterialPhase1", "mat3");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("MaterialPhase2", "mat2");

    model.param().set("W", "0.5[K]");
    model.param().descr("W", "\u76f8\u53d8\u51fd\u6570\u7684\u53c2\u6570");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "f_phtr");
    model.component("comp1").func("an1").set("expr", "exp(-((T-273.15)/W)^2)*(T<273.15)+1*(T>=273.15)");
    model.component("comp1").func("an1").set("args", "T");
    model.component("comp1").func("an1").setIndex("argunit", "K", 0);
    model.component("comp1").func("an1").setIndex("plotargs", 270, 0, 1);
    model.component("comp1").func("an1").setIndex("plotargs", 275, 0, 2);

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_initial_w");
    model.component("comp1").physics("ht").create("init2", "init", 2);
    model.component("comp1").physics("ht").feature("init2").selection().set(2);
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "T_initial_i");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_initial_w");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(9);
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(3, 6, 8);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Sw", "Sw_res+(1-Sw_res)*f_phtr(T)");
    model.component("comp1").variable("var1").descr("Sw", "\u542b\u6c34\u9971\u548c\u5ea6");
    model.component("comp1").variable("var1").set("kr", "max(10^(-Omega*epsilon_p*(1-Sw)),1e-6)");
    model.component("comp1").variable("var1").descr("kr", "\u76f8\u5bf9\u6e17\u900f\u7387");
    model.component("comp1").variable("var1").set("kappa_w", "k_int*kr");
    model.component("comp1").variable("var1").descr("kappa_w", "\u6e17\u900f\u7387");

    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("dl").feature("porous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("porous1").set("Sp", "Sw*epsilon_p*beta");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "epsilon_p*Sw");
    model.component("comp1").physics("dl").create("ms1", "MassSource", 2);
    model.component("comp1").physics("dl").feature("ms1").selection().all();
    model.component("comp1").physics("dl").feature("ms1").set("Qm", "dl.epsilon*(rho_i-rho_w)*d(Sw,t)");
    model.component("comp1").physics("dl").create("inl1", "Inlet", 1);
    model.component("comp1").physics("dl").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("dl").feature("inl1").selection().set(1);
    model.component("comp1").physics("dl").feature("inl1").set("p0", "g_const*delta_H_dLx*3[m]*rho_w");
    model.component("comp1").physics("dl").create("out1", "Outlet", 1);
    model.component("comp1").physics("dl").feature("out1").selection().set(9);
    model.component("comp1").physics("dl").feature("out1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("dl").feature("sym1").selection().set(3, 6, 8);

    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa_w"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_s"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_s"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cs"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho_w"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_w"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_w"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cw"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_i"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho_i"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"Ci"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9);
    model.result("pg1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time")
         .set("tlist", "range(0,5,60)[min] range(1.5,0.5,18) range(18.1,0.025,22) range(22.5,0.5,28) range(29,1,56)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.005);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "1[s]");

    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").copySolution("sol2");
    model.sol("sol1").runFromTo("st2", "t1");

    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 0.5, 0, 1);
    model.result().dataset("mir1").setIndex("genpoints", 3, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0.5, 1, 1);
    model.result().dataset("mir1").set("removesymelem", true);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").setIndex("looplevel", 29, 0);
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new double[]{0.02, 0.1});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").setIndex("looplevel", 29, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "degC");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6db2\u6001\u6c34\u9971\u548c\u5ea6");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").setIndex("looplevel", 29, 0);
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "Sw");
    model.result("pg3").feature("con1").set("levelmethod", "levels");
    model.result("pg3").feature("con1").set("levels", "0.05 0.5 1");
    model.result("pg3").feature("con1").set("contourtype", "filled");
    model.result("pg3").feature("con1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("con1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result().numerical().create("min1", "MinSurface");
    model.result().numerical("min1").selection().all();
    model.result().numerical("min1").set("expr", new String[]{"T"});
    model.result().numerical("min1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result().numerical("min1").set("unit", new String[]{"K"});
    model.result().numerical("min1").setIndex("unit", "degC", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u6700\u5c0f\u503c 1");
    model.result().numerical("min1").set("table", "tbl1");
    model.result().numerical("min1").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl1");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6700\u4f4e\u6e29\u5ea6");
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().set(9);
    model.result().numerical("int1").set("expr", new String[]{"ht.ntflux"});
    model.result().numerical("int1").set("descr", new String[]{"\u6cd5\u5411\u603b\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W/m"});
    model.result().numerical("int1").setIndex("expr", "ht.ntflux*dl.d*2", 0);
    model.result().numerical("int1").setIndex("descr", "\u603b\u70ed\u901a\u91cf", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl2");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u603b\u70ed\u901a\u91cf");
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").selection().all();
    model.result().numerical("int2").set("expr", new String[]{});
    model.result().numerical("int2").set("descr", new String[]{});
    model.result().numerical("int2").setIndex("expr", "Sw*epsilon_p*dl.d*2", 0);
    model.result().numerical("int2").setIndex("descr", "\u603b\u6db2\u6001\u6c34\u4f53\u79ef", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl3");
    model.result().numerical("int2").setResult();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "table");
    model.result("pg6").feature("tblp1").set("table", "tbl3");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u603b\u6db2\u6001\u6c34\u4f53\u79ef");

    model.title("\u51bb\u571f\u5939\u6742\u7269");

    model
         .description("\u672c\u4f8b\u662f\u6a21\u62df\u591a\u5b54\u4ecb\u8d28\u76f8\u53d8\u7684\u57fa\u51c6\u95ee\u9898\uff0c\u7814\u7a76\u4e86\u591a\u5b54\u4ecb\u8d28\u4e2d\u51b0\u5939\u6742\u7269\u7684\u878d\u5316\u8fc7\u7a0b\uff0c\u4ece\u800c\u8bba\u8bc1\u4e86\u5982\u4f55\u5c06\u8fbe\u897f\u5b9a\u5f8b\u4e0e\u5305\u542b\u76f8\u53d8\u7684\u201c\u591a\u5b54\u4ecb\u8d28\u4f20\u70ed\u201d\u63a5\u53e3\u76f8\u8026\u5408\u3002\u8fd9\u662f INTERFROST \u9879\u76ee\u7684\u6d4b\u8bd5\u7528\u4f8b\u4e4b\u4e00\uff0cINTERFROST \u9879\u76ee\u662f\u4e00\u4e2a\u5173\u4e8e\u591a\u5e74\u51bb\u571f\u5730\u533a\u6c14\u5019\u53d8\u5316\u7684\u70ed\u6db2\u8026\u5408\u7cfb\u7edf\u7684\u6bd4\u5bf9\u9879\u76ee\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("frozen_inclusion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
