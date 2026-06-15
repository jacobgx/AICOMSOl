/*
 * homogenization_non_newtonian_porous.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:08 by COMSOL 6.3.0.290. */
public class homogenization_non_newtonian_porous {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "pore_scale_flow_3d.stl");
    model.component("comp1").mesh("mesh1").feature("imp1").set("createdom", true);
    model.component("comp1").mesh("mesh1").feature("imp1").set("facepartition", "detectfaces");
    model.component("comp1").mesh("mesh1").feature("imp1").set("stltoltype", "absolute");
    model.component("comp1").mesh("mesh1").feature("imp1").set("stltolabs", "1e-5");
    model.component("comp1").mesh("mesh1").feature("imp1").set("facemaxangle", 62);
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.component("comp1").view("view1").set("rendermesh", true);

    model.component("comp1").mesh("mesh1").create("join1", "JoinEntities");
    model.component("comp1").mesh("mesh1").feature("join1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("join1").selection().set(14, 15, 16, 17, 18, 19, 20);
    model.component("comp1").mesh("mesh1").run("join1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("narrowreg", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(14, 36, 37);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "0.04[cm]");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(14, 36, 37);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 10);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Mesh Plot 1");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("meshdomain", "volume");
    model.result("pg1").run();

    model.param().set("rho_f", "1000[kg/m^3]");
    model.param().descr("rho_f", "Fluid density");
    model.param().set("mu_f", "1e-3[Pa*s]");
    model.param().descr("mu_f", "Fluid viscosity");
    model.param().set("u_in", "1e-4[m/s]");
    model.param().descr("u_in", "Inlet velocity");
    model.param().set("width", "2[cm]");
    model.param().descr("width", "REV width");
    model.param().set("length", "6[cm]");
    model.param().descr("length", "REV length");
    model.param().set("V_tot", "width^2*length");
    model.param().descr("V_tot", "Total REV volume");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.material().create("mat1", "Common", "");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 1);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "u_in");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("Inlet");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(16, 21);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("Outlet");
    model.component("comp1").selection("sel2").set(16, 21);

    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("Wall");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(4, 10, 30);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").all();
    model.component("comp1").selection("sel4").label("All boundaries");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("Symmetry");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel4"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1", "sel2", "sel3"});

    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("dif1");

    model.study("std1").setGenPlots(false);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("control", "stat");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-4);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 100);
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG, fluid flow variables (spf)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("Direct, fluid flow variables (spf)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-4);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 100);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().named("sel3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("Velocity");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("data", "surf1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("color", "custom");
    model.result("pg2").feature("line1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").selection().named("sel1");
    model.result("pg2").feature("str1").set("selnumber", 60);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str1").set("tuberadiusscale", 0.01);
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").set("colortable", "AuroraBorealis");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("Average Inlet");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("Average Outlet");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("por", "intop1(1)/V_tot");
    model.component("comp1").variable("var1").descr("por", "Porosity");
    model.component("comp1").variable("var1").set("dPdL", "-(aveop2(p)-aveop1(p))/length");
    model.component("comp1").variable("var1").descr("dPdL", "Pressure drop");
    model.component("comp1").variable("var1").set("u_out", "spf.out1.massFlowRate/rho_f/width^2");
    model.component("comp1").variable("var1").descr("u_out", "Superficial outlet velocity");
    model.component("comp1").variable("var1").set("kappa", "u_out*mu_f/dPdL");
    model.component("comp1").variable("var1").descr("kappa", "Permeability");

    model.sol("sol1").updateSolution();

    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"por"});
    model.result().numerical("gev1").set("descr", new String[]{"Porosity"});
    model.result().numerical("gev1").set("expr", new String[]{"por", "kappa"});
    model.result().numerical("gev1").set("descr", new String[]{"Porosity", "Permeability"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("pointtype", "interactivearrow");
    model.result("pg2").feature("str1").set("localtimeshifts", 2000);
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("sweeptype", "streamline");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export("anim1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("localtime", 29000);

    model.title("\u5728\u5fae\u89c2\u5c3a\u5ea6\u4e0a\u5206\u6790\u591a\u5b54\u7ed3\u6784");

    model
         .description("\u7531\u4e8e\u5b9e\u9645\u591a\u5b54\u7ed3\u6784\u672c\u8eab\u7684\u590d\u6742\u6027\uff0c\u6211\u4eec\u5f88\u96be\u5bf9\u5176\u4e2d\u7684\u6d41\u52a8\u8fdb\u884c\u5efa\u6a21\u3002\u5728\u5b9e\u9645\u5e94\u7528\u4e2d\uff0c\u8be6\u7ec6\u89e3\u6790\u6d41\u573a\u662f\u4e0d\u53ef\u884c\u7684\u3002\u56e0\u6b64\uff0c\u9700\u8981\u91c7\u7528\u5b8f\u89c2\u65b9\u6cd5\u6765\u5229\u7528\u591a\u5b54\u7ed3\u6784\u7684\u5e73\u5747\u91cf\uff0c\u6bd4\u5982\u5b54\u9699\u7387\u548c\u6e17\u900f\u7387\u3002\u672c\u4f8b\u5bfc\u5165\u4e00\u4e2a\u591a\u5b54\u7ed3\u6784\u7684 STL \u6587\u4ef6\uff0c\u5e76\u8be6\u7ec6\u5206\u6790\u5b54\u9699\u5c3a\u5ea6\u4e0b\u7684\u6d41\u573a\u3002");

    model.label("pore_scale_flow_3d.mph");

    model.result("pg2").run();
    model.result("pg2").run();

    model.param().set("poro", "0.3709");
    model.param().descr("poro", "Porosity");
    model.param().set("kappa0", "3.029e-8[m^2]");
    model.param().descr("kappa0", "Permeability");
    model.param().set("muc0", "0.1[Pa*s]");
    model.param().descr("muc0", "Zero shear rate viscosity, Carreau fluid");
    model.param().set("muc_inf", "0.005[Pa*s]");
    model.param().descr("muc_inf", "Infinite shear rate viscosity");
    model.param().set("lambda", "1.5[s]");
    model.param().descr("lambda", "Relaxation time");
    model.param().set("n", "0.65");
    model.param().descr("n", "Power index");

    model.component("comp1").physics("spf").feature("fp1").set("Constitutiverelation", "InelasticNonNewtonian");
    model.component("comp1").physics("spf").feature("fp1").set("nonNewtonianModels", "Carreau");

    model.material("mat1").propertyGroup().create("Carreau", "Carreau", "Non-Newtonian_Carreau_model");
    model.material("mat1").propertyGroup("Carreau").set("mu0", new String[]{"muc0"});
    model.material("mat1").propertyGroup("Carreau").set("mu_inf", new String[]{"muc_inf"});
    model.material("mat1").propertyGroup("Carreau").set("lam_car", new String[]{"lambda"});
    model.material("mat1").propertyGroup("Carreau").set("n_car", new String[]{"n"});

    model.component("comp1").variable("var1").set("mu_app", "kappa0/u_out*dPdL");
    model.component("comp1").variable("var1").descr("mu_app", "Apparent viscosity");
    model.component("comp1").variable("var1")
         .set("gamma_app", "sqrt(((mu_app-muc_inf)/(muc0-muc_inf))^(2/(n-1))-1)/lambda");

    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");

    model.param().set("p_in", "1[Pa]", "\u5165\u53e3\u538b\u529b");

    model.component("comp1").physics("spf").feature("inl1").set("p0", "p_in");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "rho_f", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "kg/m^3", 0);
    model.study("std2").feature("stat").setIndex("pname", "rho_f", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "kg/m^3", 0);
    model.study("std2").feature("stat").setIndex("pname", "p_in", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "10^range(0,0.5,2)", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("pointtype", "none");
    model.result("pg3").feature("str1").set("selnumber", 80);
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("expr", "spf.mu");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "Lichen");
    model.result("pg3").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "u_out/sqrt(poro*kappa0)", 0);
    model.result().numerical("gev2").setIndex("expr", "gamma_app", 1);
    model.result().numerical("gev2").setIndex("expr", "mu_app", 2);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl2");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("xaxisdata", 2);
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg4").run();
    model.result("pg4").run();

    model.func().create("lsq1", "LeastSquares");
    model.func("lsq1").set("source", "resultTable");
    model.func("lsq1").set("resultTable", "tbl2");
    model.func("lsq1").setEntry("columnType", "col1", "none");
    model.func("lsq1").setEntry("columnType", "col2", "arg");
    model.func("lsq1").setEntry("columnType", "col3", "value");
    model.func("lsq1").set("pname", new String[]{});
    model.func("lsq1").set("plist", new int[]{});
    model.func("lsq1").setEntry("exprs", "col3", "alpha*x1");
    model.func("lsq1").setIndex("pname", "alpha", 0);
    model.func("lsq1").setIndex("plist", 0, 0);
    model.func("lsq1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"2[cm]", "6[cm]", "2[cm]"});

    model.component("comp2").physics().create("br", "PorousMediaFlowBrinkman", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/br", false);
    model.study("std2").feature("stat").setSolveFor("/physics/br", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("br").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1").set("rho", "rho_f");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1")
         .set("Constitutiverelation", "InelasticNonNewtonian");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1").set("nonNewtonianModels", "Carreau");
    model.component("comp2").physics("br").feature("porous1").feature("fluid1").set("alpha", "lsq1.alpha");

    model.component("comp2").material().create("matlnk2", "Link");
    model.material("mat1").propertyGroup("def").set("porosity", new String[]{"poro"});
    model.material("mat1").propertyGroup("def").set("hydraulicpermeability", new String[]{"kappa0"});

    model.component("comp2").physics("br").create("inl1", "InletBoundary", 2);
    model.component("comp2").physics("br").feature("inl1").selection().set(2);
    model.component("comp2").physics("br").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp2").physics("br").feature("inl1").set("p0", "p_in");
    model.component("comp2").physics("br").create("out1", "OutletBoundary", 2);
    model.component("comp2").physics("br").feature("out1").selection().set(5);
    model.component("comp2").physics("br").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("br").feature("sym1").selection().set(1, 3, 4, 6);

    model.component("comp2").cpl().create("aveop3", "Average");
    model.component("comp2").cpl("aveop3").set("axisym", true);
    model.component("comp2").cpl("aveop3").selection().all();

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2")
         .set("u_out", "br.out1.massFlowRate/rho_f/width^2", "Superficial outlet velocity");
    model.component("comp2").variable("var2").descr("u_out", "Darcy velocity");
    model.component("comp2").variable("var2").set("mu_app", "aveop3(br.mu)", "Apparent viscosity");
    model.component("comp2").variable("var2").set("gamma_app", "aveop3(br.porous.fluid.gamma_app)");
    model.component("comp2").variable("var2").descr("gamma_app", "Apparent shear rate");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std3").feature("stat").setSolveFor("/physics/br", true);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "rho_f", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "kg/m^3", 0);
    model.study("std3").feature("stat").setIndex("pname", "rho_f", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "kg/m^3", 0);
    model.study("std3").feature("stat").setIndex("pname", "p_in", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "10^range(0,0.5,2)", 0);
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").set("data", "dset4");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("table", "tbl3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg5").run();
    model.result("pg5").feature("tblp2").set("plotcolumns", new int[]{4});
    model.result("pg5").run();
    model.result("pg3").run();

    model.title("\u975e\u725b\u987f\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\u7684\u5747\u8d28\u5316");

    model
         .description("\u975e\u725b\u987f\u6d41\u4f53\u5177\u6709\u968f\u526a\u5207\u901f\u7387\u53d8\u5316\u7684\u590d\u6742\u6d41\u52a8\u7279\u6027\uff0c\u4f7f\u5176\u5728\u591a\u5b54\u6750\u6599\u4e2d\u7684\u884c\u4e3a\u96be\u4ee5\u9884\u6d4b\u3002\u5b54\u9699\u5c3a\u5ea6\u5efa\u6a21\u80fd\u591f\u5728\u5fae\u89c2\u5c42\u9762\u6355\u6349\u8fd9\u4e9b\u6d41\u578b\uff0c\u6709\u52a9\u4e8e\u4e3a\u5b8f\u89c2\u5c3a\u5ea6\u7684\u5e94\u7528\u63a8\u5bfc\u51fa\u5fc5\u8981\u7684\u5c5e\u6027\u3002\n\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u5bf9\u5fae\u5b54\u6570\u636e\u8fdb\u884c\u6700\u5c0f\u4e8c\u4e58\u62df\u5408\uff0c\u786e\u5b9a\u5b8f\u89c2\u5efa\u6a21\u4e2d\u7684\u8868\u89c2\u526a\u5207\u901f\u7387\u3002\u6b64\u5916\uff0c\u8fd8\u5bf9\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u9a8c\u8bc1\u4e86\u8be5\u65b9\u6cd5\u5728\u5404\u79cd\u5e94\u7528\u4e2d\u7684\u4ef7\u503c\u3002");

    model.component("comp2").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("homogenization_non_newtonian_porous.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
