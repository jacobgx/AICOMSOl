/*
 * parameter_estimation_polymer_viscoplasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:39 by COMSOL 6.3.0.290. */
public class parameter_estimation_polymer_viscoplasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoplasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.baseSystem("mpa");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u538b\u7f29\uff0c0.001 1/s\uff0cT=293 K");
    model.result().table("tbl1")
         .importData("parameter_estimation_polymer_viscoplasticity_compression_1e-3_T293K.txt");
    model.result().table().duplicate("tbl2", "tbl1");
    model.result().table("tbl2").label("\u538b\u7f29\uff0c0.1 1/s\uff0cT=293 K");
    model.result().table("tbl2")
         .importData("parameter_estimation_polymer_viscoplasticity_compression_1e-1_T293K.txt");
    model.result().table().duplicate("tbl3", "tbl2");
    model.result().table("tbl3").label("\u62c9\u4f38\uff0c0.001 1/s\uff0cT=293 K");
    model.result().table("tbl3").importData("parameter_estimation_polymer_viscoplasticity_tension_1e-3_T293K.txt");
    model.result().table().duplicate("tbl4", "tbl3");
    model.result().table("tbl4").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=293 K");
    model.result().table("tbl4").importData("parameter_estimation_polymer_viscoplasticity_tension_1e-1_T293K.txt");
    model.result().table().duplicate("tbl5", "tbl4");
    model.result().table("tbl5").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=310 K");
    model.result().table("tbl5").importData("parameter_estimation_polymer_viscoplasticity_tension_1e-1_T310K.txt");
    model.result().table().duplicate("tbl6", "tbl5");
    model.result().table("tbl6").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=323 K");
    model.result().table("tbl6").importData("parameter_estimation_polymer_viscoplasticity_tension_1e-1_T323K.txt");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b-\u5e94\u53d8\u6570\u636e");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5bf9\u6570\u5e94\u53d8 (1)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u67ef\u897f\u5e94\u529b (MPa)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").label("\u538b\u7f29\uff0c0.001 1/s\uff0cT=293 K");
    model.result("pg1").feature("tblp1").set("xaxisdata", 2);
    model.result("pg1").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg1").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg1").feature("tblp1").set("linestyle", "dotted");
    model.result("pg1").feature("tblp1").set("linemarker", "point");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("autoheaders", false);
    model.result("pg1").feature("tblp1").set("autoplotlabel", true);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").label("\u538b\u7f29\uff0c0.1 1/s\uff0cT=293 K");
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature().duplicate("tblp3", "tblp2");
    model.result("pg1").run();
    model.result("pg1").feature("tblp3").label("\u62c9\u4f38\uff0c0.001 1/s\uff0cT=293 K");
    model.result("pg1").feature("tblp3").set("table", "tbl3");
    model.result("pg1").feature().duplicate("tblp4", "tblp3");
    model.result("pg1").run();
    model.result("pg1").feature("tblp4").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=293 K");
    model.result("pg1").feature("tblp4").set("table", "tbl4");
    model.result("pg1").run();

    model.param().label("\u6750\u6599\u53c2\u6570");
    model.param().set("mu0_eq", "2[MPa]");
    model.param().descr("mu0_eq", "\u526a\u5207\u6a21\u91cf\uff0c\u5e73\u8861\u7f51\u7edc");
    model.param().set("Nsegm", "10");
    model.param().descr("Nsegm", "\u94fe\u6bb5\u6570");
    model.param().set("K", "1[GPa]");
    model.param().descr("K", "\u4f53\u79ef\u6a21\u91cf");
    model.param().set("beta", "1");
    model.param().descr("beta", "\u80fd\u91cf\u56e0\u5b50");
    model.param().set("A", "1[s^-1]");
    model.param().descr("A", "\u9ecf\u5851\u6027\u7387\u7cfb\u6570");
    model.param().set("c", "-0.5");
    model.param().descr("c", "\u5e94\u53d8\u786c\u5316\u6307\u6570");
    model.param().set("n", "5");
    model.param().descr("n", "\u5e94\u529b\u786c\u5316\u6307\u6570");
    model.param().set("m", "10");
    model.param().descr("m", "\u6e29\u5ea6\u786c\u5316\u6307\u6570");
    model.param().set("sig_res", "sqrt(3)[MPa]");
    model.param().descr("sig_res", "\u6d41\u963b");
    model.param().create("par2");
    model.param("par2").label("\u8f7d\u8377\u53c2\u6570");
    model.param("par2").set("strain_rate", "1e-3[s^-1]");
    model.param("par2").descr("strain_rate", "\u6807\u79f0\u5e94\u53d8\u7387");
    model.param("par2").set("t_end", "1/strain_rate");
    model.param("par2").descr("t_end", "\u52a0\u8f7d\u6301\u7eed\u65f6\u95f4");
    model.param("par2").set("emax_ten", "0.6");
    model.param("par2").descr("emax_ten", "\u6807\u79f0\u5e94\u53d8\uff0c\u62c9\u4f38\u8f7d\u8377\u5de5\u51b5");
    model.param("par2").set("emax_comp", "-0.3");
    model.param("par2").descr("emax_comp", "\u6807\u79f0\u5e94\u53d8\uff0c\u538b\u7f29\u8f7d\u8377\u5de5\u51b5");
    model.param("par2").set("Tref", "293.15[K]");
    model.param("par2").descr("Tref", "\u53c2\u8003\u6e29\u5ea6");
    model.param("par2").set("T", "293.15[K]");
    model.param("par2").descr("T", "\u6e29\u5ea6");

    model.func().create("tri1", "Triangle");
    model.func("tri1").label("\u8f7d\u8377\u5faa\u73af");
    model.func("tri1").set("lower", 0);
    model.func("tri1").set("upper", 2);
    model.func("tri1").set("smoothactive", false);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 2, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 2, 0});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Bergstrom-Boyce \u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1[g/cm^3]"});
    model.component("comp1").material("mat1").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.component("comp1").material("mat1").propertyGroup()
         .create("BergstromBoyce", "BergstromBoyce", "Bergstrom-Boyce_viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("Nseg", new String[]{"Nsegm"});
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce")
         .set("mu0", new String[]{"mu0_eq*(1+(T-Tref)/Tref)"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce").set("A_BB", new String[]{"A"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce")
         .set("sigRes_BB", new String[]{"sig_res"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce").set("n_BB", new String[]{"n"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce").set("c_BB", new String[]{"c"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBoyce")
         .set("sigmaco_BB", new String[]{"0[MPa]"});

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().set(1, 2);
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "ArrudaBoyce");
    model.component("comp1").physics("solid").feature("hmm1").set("VolumetricEnergyUncoupled", "miehe");
    model.component("comp1").physics("solid").feature("hmm1").set("kappa", "K");
    model.component("comp1").physics("solid").feature("hmm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("hmm1").create("pvp1", "PolymerViscoplasticity", 3);
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav", "beta");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("thermalEffects", "powerLaw");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("T0", "Tref");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("m", "m");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("timeMethod", "ode");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("minput_temperature", "T");
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2, 3, 6, 7, 8);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").label("\u5355\u8f74\u538b\u7f29");
    model.component("comp1").physics("solid").feature("disp1").selection().set(11);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "emax_comp*tri1(t/t_end)*1[mm]", 0);
    model.component("comp1").physics("solid").feature().duplicate("disp2", "disp1");
    model.component("comp1").physics("solid").feature("disp2").label("\u5355\u8f74\u62c9\u4f38");
    model.component("comp1").physics("solid").feature("disp2").selection().set(12);
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "emax_ten*tri1(t/t_end)*1[mm]", 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().set(1);
    model.component("comp1").cpl().duplicate("aveop2", "aveop1");
    model.component("comp1").cpl("aveop2").selection().set(2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5168\u5c40\u5e94\u529b\u548c\u5e94\u53d8\u53d8\u91cf");
    model.component("comp1").variable("var1").set("sig_comp", "aveop1(solid.sxx)");
    model.component("comp1").variable("var1").descr("sig_comp", "\u67ef\u897f\u5e94\u529b\uff0c\u538b\u7f29");
    model.component("comp1").variable("var1").set("elog_comp", "aveop1(solid.elogxx)");
    model.component("comp1").variable("var1").descr("elog_comp", "\u5bf9\u6570\u5e94\u53d8\uff0c\u538b\u7f29");
    model.component("comp1").variable("var1").set("sig_ten", "aveop2(solid.sxx)");
    model.component("comp1").variable("var1").descr("sig_ten", "\u67ef\u897f\u5e94\u529b\uff0c\u62c9\u4f38");
    model.component("comp1").variable("var1").set("elog_ten", "aveop2(solid.elogxx)");
    model.component("comp1").variable("var1").descr("elog_ten", "\u5bf9\u6570\u5e94\u53d8\uff0c\u62c9\u4f38");

    model.study("std1").label("\u6b63\u6f14\u95ee\u9898");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "A", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("pname", "A", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("pname", "strain_rate", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.001 0.1", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.02,1)*2*t_end");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evp").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pw").set("scaleval", "1[MPa]");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "emax_ten");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("consistent", false);
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 25);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u521d\u59cb\u6a21\u578b\u9884\u6d4b");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").label("\u521d\u59cb\u9884\u6d4b\uff0c\u538b\u7f29");
    model.result("pg2").feature("glob1").setIndex("expr", "sig_comp", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "elog_comp");
    model.result("pg2").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").label("\u521d\u59cb\u9884\u6d4b\uff0c\u62c9\u4f38");
    model.result("pg2").feature("glob2").setIndex("expr", "sig_ten", 0);
    model.result("pg2").feature("glob2").set("xdataexpr", "elog_ten");
    model.result("pg2").feature("glob2").set("linecolor", "cycle");
    model.result("pg2").run();

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").label("\u538b\u7f29\uff0c0.001 1/s\uff0cT=293 K");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").setEntry("columnType", "col2", "none");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col3", "comp1.sig_comp");
    model.component("comp1").common("lso1").setEntry("variableName", "col3", "comp_slow");
    model.component("comp1").common("lso1").setEntry("unit", "col3", "MPa");
    model.component("comp1").common("lso1").setIndex("paramNames", "mu0_eq", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso1").setIndex("paramNames", "mu0_eq", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso1").setIndex("paramNames", "strain_rate", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "0.001[s^-1]", 0);
    model.component("comp1").common().duplicate("lso2", "lso1");
    model.component("comp1").common("lso2").label("\u538b\u7f29\uff0c0.1 1/s\uff0cT=293 K");
    model.component("comp1").common("lso2").set("resultTable", "tbl2");
    model.component("comp1").common("lso2").setEntry("variableName", "col3", "comp_fast");
    model.component("comp1").common("lso2").setIndex("paramExprs", "0.1[s^-1]", 0);
    model.component("comp1").common().duplicate("lso3", "lso2");
    model.component("comp1").common("lso3").label("\u62c9\u4f38\uff0c0.001 1/s\uff0cT=293 K");
    model.component("comp1").common("lso3").set("resultTable", "tbl3");
    model.component("comp1").common("lso3").setEntry("modelExpression", "col3", "comp1.sig_ten");
    model.component("comp1").common("lso3").setEntry("variableName", "col3", "ten_slow");
    model.component("comp1").common("lso3").setIndex("paramExprs", "0.001[s^-1]", 0);
    model.component("comp1").common().duplicate("lso4", "lso3");
    model.component("comp1").common("lso4").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=293 K");
    model.component("comp1").common("lso4").set("resultTable", "tbl4");
    model.component("comp1").common("lso4").setEntry("variableName", "col3", "ten_fast");
    model.component("comp1").common("lso4").setIndex("paramExprs", "0.1[s^-1]", 0);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u53c2\u6570\u4f30\u8ba1");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").set("source", "none");
    model.study("std2").feature("lsqo").setIndex("pname", "A", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "1[s^-1]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "A", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "1[s^-1]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "beta", 1);
    model.study("std2").feature("lsqo").setIndex("initval", 1, 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "beta", 1);
    model.study("std2").feature("lsqo").setIndex("initval", 1, 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "c", 2);
    model.study("std2").feature("lsqo").setIndex("initval", -0.5, 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "c", 2);
    model.study("std2").feature("lsqo").setIndex("initval", -0.5, 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "emax_comp", 3);
    model.study("std2").feature("lsqo").setIndex("initval", -0.3, 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "emax_comp", 3);
    model.study("std2").feature("lsqo").setIndex("initval", -0.3, 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "emax_ten", 4);
    model.study("std2").feature("lsqo").setIndex("initval", 0.6, 4);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 4);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 4);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 4);
    model.study("std2").feature("lsqo").setIndex("pname", "emax_ten", 4);
    model.study("std2").feature("lsqo").setIndex("initval", 0.6, 4);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 4);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 4);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 4);
    model.study("std2").feature("lsqo").setIndex("pname", "K", 5);
    model.study("std2").feature("lsqo").setIndex("initval", "1[GPa]", 5);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 5);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 5);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 5);
    model.study("std2").feature("lsqo").setIndex("pname", "K", 5);
    model.study("std2").feature("lsqo").setIndex("initval", "1[GPa]", 5);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 5);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 5);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 5);
    model.study("std2").feature("lsqo").setIndex("pname", "mu0_eq", 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 0);
    model.study("std2").feature("lsqo").setIndex("pname", "Nsegm", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 10, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", 2, 1);
    model.study("std2").feature("lsqo").setIndex("pname", "beta", 2);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 2);
    model.study("std2").feature("lsqo").setIndex("pname", "A", 3);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 3);
    model.study("std2").feature("lsqo").setIndex("pname", "c", 4);
    model.study("std2").feature("lsqo").setIndex("lbound", -1, 4);
    model.study("std2").feature("lsqo").setIndex("ubound", 0, 4);
    model.study("std2").feature("lsqo").setIndex("pname", "n", 5);
    model.study("std2").feature("lsqo").setIndex("lbound", 1.01, 5);
    model.study("std2").feature("lsqo").setIndex("ubound", 20, 5);
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("v1").feature("comp1_solid_hmm1_pvp1_evp").set("scaleval", 1);
    model.sol("sol5").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe").set("scaleval", 1);
    model.sol("sol5").feature("v1").feature("comp1_solid_hmm1_pw").set("scaleval", 1);
    model.sol("sol5").feature("v1").feature("comp1_u").set("scaleval", "emax_ten");
    model.sol("sol5").feature("o1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol5").feature("o1").feature("t1").set("consistent", false);
    model.sol("sol5").feature("o1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol5").feature("o1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol5").feature("o1").feature("t1").feature("fc1").set("maxiter", 25);

    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u53c2\u6570\u4f30\u8ba1\uff1aBergstrom-Boyce \u9ecf\u5851\u6027");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").label("\u6a21\u578b\u9884\u6d4b\uff0c\u538b\u7f29 0.001 1/s");
    model.result("pg3").feature("glob1").setIndex("expr", "lso1.comp_slow.model", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "elog_comp");
    model.result("pg3").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").label("\u6a21\u578b\u9884\u6d4b\uff0c\u538b\u7f29 0.1 1/s");
    model.result("pg3").feature("glob2").setIndex("expr", "lso2.comp_fast.model", 0);
    model.result("pg3").feature("glob2").set("linecolor", "cycle");
    model.result("pg3").feature().duplicate("glob3", "glob2");
    model.result("pg3").run();
    model.result("pg3").feature("glob3").label("\u6a21\u578b\u9884\u6d4b\uff0c\u62c9\u4f38 0.001 1/s");
    model.result("pg3").feature("glob3").setIndex("expr", "lso3.ten_slow.model", 0);
    model.result("pg3").feature("glob3").set("xdataexpr", "elog_ten");
    model.result("pg3").feature().duplicate("glob4", "glob3");
    model.result("pg3").run();
    model.result("pg3").feature("glob4").label("\u6a21\u578b\u9884\u6d4b\uff0c\u62c9\u4f38 0.1 1/s");
    model.result("pg3").feature("glob4").setIndex("expr", "lso4.ten_fast.model", 0);

    model.study("std2").feature("lsqo").set("plot", true);
    model.study("std2").feature("lsqo").set("plotgroup", "pg3");
    model.study("std2").feature("lsqo").set("showindobj", true);
    model.study("std2").feature("lsqo").set("plotobj", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg3").run();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.component("comp1").physics("solid").feature().duplicate("hmm2", "hmm1");
    model.component("comp1").physics("solid").feature("hmm2").set("mu0_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2")
         .set("mu0", "withsol('sol5', mu0_eq)*(1+(T-Tref)/Tref)");
    model.component("comp1").physics("solid").feature("hmm2").set("Nseg_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").set("Nseg", "withsol('sol5', Nsegm)");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("betav", "withsol('sol5', beta)");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("A_BB_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("A_BB", "withsol('sol5', A)");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("sigRes_BB_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("sigRes_BB", "sig_res");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("n_BB_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("n_BB", "withsol('sol5', n)");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("sigmaco_BB_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("c_BB_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").feature("pvp1").set("c_BB", "withsol('sol5', c)");

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b-\u5e94\u53d8\u6570\u636e\uff1a\u6e29\u5ea6\u4f9d\u5b58\u6027");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("table", "tbl4");
    model.result("pg5").feature("tblp1").set("xaxisdata", 2);
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg5").feature("tblp1").set("linestyle", "dotted");
    model.result("pg5").feature("tblp1").set("linemarker", "point");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("autoplotlabel", true);
    model.result("pg5").feature("tblp1").set("autoheaders", false);
    model.result("pg5").feature("tblp1").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=293 K");
    model.result("pg5").feature().duplicate("tblp2", "tblp1");
    model.result("pg5").run();
    model.result("pg5").feature("tblp2").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=310 K");
    model.result("pg5").feature("tblp2").set("table", "tbl5");
    model.result("pg5").feature().duplicate("tblp3", "tblp2");
    model.result("pg5").run();
    model.result("pg5").feature("tblp3").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=323 K");
    model.result("pg5").feature("tblp3").set("table", "tbl6");
    model.result("pg5").run();

    model.component("comp1").common().duplicate("lso5", "lso4");
    model.component("comp1").common("lso5").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=310 K");
    model.component("comp1").common("lso5").set("resultTable", "tbl5");
    model.component("comp1").common("lso5").setEntry("variableName", "col3", "ten_fast_310K");
    model.component("comp1").common("lso5").setIndex("paramNames", "mu0_eq", 1);
    model.component("comp1").common("lso5").setIndex("paramExprs", "", 1);
    model.component("comp1").common("lso5").setIndex("paramNames", "mu0_eq", 1);
    model.component("comp1").common("lso5").setIndex("paramExprs", "", 1);
    model.component("comp1").common("lso5").setIndex("paramNames", "T", 1);
    model.component("comp1").common("lso5").setIndex("paramExprs", "310.15[K]", 1);
    model.component("comp1").common().duplicate("lso6", "lso5");
    model.component("comp1").common("lso6").label("\u62c9\u4f38\uff0c0.1 1/s\uff0cT=323 K");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").common("lso6").set("resultTable", "tbl6");
    model.component("comp1").common("lso6").setEntry("variableName", "col3", "ten_fast_323K");
    model.component("comp1").common("lso6").setIndex("paramExprs", "323.15[K]", 1);

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u53c2\u6570\u4f30\u8ba1\uff1a\u6e29\u5ea6\u4f9d\u5b58\u6027");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("lsqo", "LSQOptimization");
    model.study("std3").feature("lsqo").set("source", "none");
    model.study("std3").feature("lsqo").setIndex("objectiveActive", false, 0);
    model.study("std3").feature("lsqo").setIndex("objectiveActive", false, 1);
    model.study("std3").feature("lsqo").setIndex("objectiveActive", false, 2);
    model.study("std3").feature("lsqo").setIndex("pname", "A", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "1[s^-1]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "A", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "1[s^-1]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "m", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 10, 0);
    model.study("std3").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"solid/hmm1"});
    model.study("std3").showAutoSequences("all");

    model.sol("sol6").feature("v1").feature("comp1_solid_hmm2_pvp1_evp").set("scaleval", 1);
    model.sol("sol6").feature("v1").feature("comp1_solid_hmm2_pvp1_evpe").set("scaleval", 1);
    model.sol("sol6").feature("v1").feature("comp1_solid_hmm2_pw").set("scaleval", 1);
    model.sol("sol6").feature("v1").feature("comp1_u").set("scaleval", "emax_ten");
    model.sol("sol6").feature("o1").set("stationaryhidesens", "off");
    model.sol("sol6").feature("o1").set("stationaryhideadj", "off");
    model.sol("sol6").feature("o1").set("diffint", "1E-3");
    model.sol("sol6").feature("o1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol6").feature("o1").feature("t1").set("consistent", false);
    model.sol("sol6").feature("o1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol6").feature("o1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol6").feature("o1").feature("t1").feature("fc1").set("maxiter", 25);

    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.study("std3").feature("lsqo").set("probewindow", "");

    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u53c2\u6570\u4f30\u8ba1\uff1a\u6e29\u5ea6\u4f9d\u5b58\u6027");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").label("\u6a21\u578b\u9884\u6d4b\uff0c\u62c9\u4f38 0.1 1/s\uff0cT=293 K");
    model.result("pg6").feature("glob1").set("data", "dset4");
    model.result("pg6").feature("glob1").setIndex("looplevelinput", "manual", 2);
    model.result("pg6").feature("glob1").setIndex("looplevel", new int[]{1}, 2);
    model.result("pg6").feature("glob1").setIndex("expr", "lso4.ten_fast.model", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "elog_ten");
    model.result("pg6").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").label("\u6a21\u578b\u9884\u6d4b\uff0c\u62c9\u4f38 0.1 1/s\uff0cT=310 K");
    model.result("pg6").feature("glob2").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg6").feature("glob2").setIndex("expr", "lso5.ten_fast_310K.model", 0);
    model.result("pg6").feature("glob2").set("linecolor", "cycle");
    model.result("pg6").feature().duplicate("glob3", "glob2");
    model.result("pg6").run();
    model.result("pg6").feature("glob3").label("\u6a21\u578b\u9884\u6d4b\uff0c\u62c9\u4f38 0.1 1/s\uff0cT=323 K");
    model.result("pg6").feature("glob3").setIndex("looplevel", new int[]{3}, 2);
    model.result("pg6").feature("glob3").setIndex("expr", "lso6.ten_fast_323K.model", 0);
    model.result("pg6").run();
    model.result().evaluationGroup().create("std2lsqoparam1", "EvaluationGroup");
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").set("data", "dset3");
    model.result().evaluationGroup("std2lsqoparam1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std2lsqoparam1").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("std2lsqoparam1").set("transpose", true);
    model.result().evaluationGroup("std2lsqoparam1").set("includeparameters", false);
    model.result().evaluationGroup("std2lsqoparam1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "mu0_eq", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "Nsegm", 1);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "beta", 2);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "A", 3);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "c", 4);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "n", 5);
    model.result().evaluationGroup("std2lsqoparam1").run();
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").label("\u5df2\u6821\u51c6\u7684\u6750\u6599\u53c2\u6570");
    model.result().evaluationGroup("std2lsqoparam1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").set("data", "dset4");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("looplevelinput", "last", 2);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev2").setIndex("expr", "m", 0);
    model.result().evaluationGroup("std2lsqoparam1").run();

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/hmm2"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"solid/hmm2"});

    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").label("\u76ee\u6807\u51fd\u6570");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u76ee\u6807");
    model.result("pg4").set("ylog", true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("data", "dset3");
    model.result("pg3").feature("glob1").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").feature("glob1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("data", "dset3");
    model.result("pg3").feature("glob2").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").feature("glob2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg3").run();
    model.result("pg3").feature("glob3").set("data", "dset3");
    model.result("pg3").feature("glob3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").feature("glob3").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg3").feature("glob3").set("xdatasolnumtype", "level1");
    model.result("pg3").run();
    model.result("pg3").feature("glob4").set("data", "dset3");
    model.result("pg3").feature("glob4").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").feature("glob4").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg3").feature("glob4").set("xdatasolnumtype", "level1");
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u9ecf\u5851\u6027\u805a\u5408\u7269\u7684\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u9002\u7528\u4e8e\u7c7b\u6a61\u80f6\u6750\u6599\u975e\u5e73\u8861\u5efa\u6a21\u7684\u9ecf\u5851\u6027 Bergstrom-Boyce \u6a21\u578b\u7684\u6750\u6599\u53c2\u6570\uff0c\u7528\u4e8e\u53c2\u6570\u4f30\u8ba1\u7684\u6570\u636e\u5305\u62ec\u4e24\u79cd\u4e0d\u540c\u5e94\u53d8\u7387\u4e0b\u7684\u5faa\u73af\u5355\u8f74\u62c9\u4f38\u548c\u538b\u7f29\u8bd5\u9a8c\u6570\u636e\u3002\u83b7\u5f97\u4e3b\u8981\u6a21\u578b\u53c2\u6570\u540e\uff0c\u63a5\u7740\u5229\u7528\u6765\u81ea\u4e0d\u540c\u6e29\u5ea6\u4e0b\u62c9\u4f38\u5b9e\u9a8c\u7684\u989d\u5916\u6570\u636e\uff0c\u5bf9\u6a21\u578b\u516c\u5f0f\u8fdb\u884c\u4e86\u6269\u5c55\uff0c\u4f7f\u5176\u4e5f\u5305\u542b\u6e29\u5ea6\u4f9d\u5b58\u6027\u3002");

    model.label("parameter_estimation_polymer_viscoplasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
