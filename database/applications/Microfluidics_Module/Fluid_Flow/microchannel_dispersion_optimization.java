/*
 * microchannel_dispersion_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:07 by COMSOL 6.3.0.290. */
public class microchannel_dispersion_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r1", "50[um]", "\u5185\u534a\u5f84");
    model.param().set("r2", "150[um]", "\u5916\u534a\u5f84");
    model.param().set("L", "1000[um]", "\u77e9\u5f62\u901a\u9053\u957f\u5ea6");
    model.param().set("kk", "0.55228474", "\u8d1d\u585e\u5c14\u66f2\u7ebf\u53c2\u6570");
    model.param().set("RL", "200[um]", "\u6062\u590d\u957f\u5ea6");
    model.param().set("IL", "700[um]", "\u79ef\u5206\u957f\u5ea6");
    model.param().set("P1", "50[um]", "\u4f18\u5316\u53c2\u6570 1");
    model.param().set("P2", "50[um]", "\u4f18\u5316\u53c2\u6570 2");
    model.param().set("P3", "0.5", "\u4f18\u5316\u53c2\u6570 3");
    model.param().set("P4", "0.5", "\u4f18\u5316\u53c2\u6570 4");
    model.param().set("P5", "0.5", "\u4f18\u5316\u53c2\u6570 5");
    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("V0", "20[V]", "\u7535\u52bf");
    model.param("par2").set("er_w", "80", "\u6c34\u7684\u4ecb\u7535\u5e38\u6570");
    model.param("par2").set("ew", "er_w*epsilon0_const", "\u6c34\u7684\u7535\u5bb9\u7387");
    model.param("par2").set("zeta", "-0.1[V]", "\u901a\u9053\u58c1\u4e0a\u7684 Zeta \u7535\u4f4d");
    model.param("par2").set("sigma", "20[um]", "\u9ad8\u65af\u8109\u51b2\u6807\u51c6\u5dee");
    model.param("par2").set("D", "1e-11[m^2/s]", "\u7269\u8d28\u6269\u6563\u7cfb\u6570");
    model.param("par2").set("rho0", "1000[kg/m^3]", "\u5bc6\u5ea6");
    model.param("par2").set("mu0", "1e-3[Pa*s]", "\u9ecf\u5ea6");
    model.param("par2").set("L_av", "2*L+pi*(r2-r1)", "\u603b\u5e73\u5747\u901a\u9053\u957f\u5ea6");
    model.param("par2").set("E_av", "V0/L_av", "\u57fa\u4e8e\u5e73\u5747\u957f\u5ea6\u7684\u7535\u573a");
    model.param("par2").set("u_av", "abs(ew*zeta*E_av/mu0)", "\u5e73\u5747\u6ed1\u79fb\u901f\u5ea6");
    model.param("par2").set("Pe", "u_av*(r2-r1)/D", "\u4f69\u514b\u83b1\u7279\u6570");
    model.param("par2").set("Re", "u_av*(r2-r1)*rho0/mu0", "\u96f7\u8bfa\u6570");
    model.param("par2").set("xm", "-600[um]", "\u9ad8\u65af\u8109\u51b2\u4f4d\u7f6e");
    model.param("par2").set("c0", "1[mol/m^3]", "\u521d\u59cb\u5cf0\u503c\u6d53\u5ea6");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

    model.component("comp1").geom("geom1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", "r2", 1, 0);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", "kk*r2", 0, 1);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", "r2", 1, 1);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", "r2", 0, 2);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", "kk*r2", 1, 2);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", "r2", 0, 3);
    model.component("comp1").geom("geom1").run("cb1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"r2", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"P1", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("cb2", "CubicBezier");
    model.component("comp1").geom("geom1").feature("cb2").setIndex("p", "P1", 1, 0);
    model.component("comp1").geom("geom1").feature("cb2").setIndex("p", "kk*P1", 0, 1);
    model.component("comp1").geom("geom1").feature("cb2").setIndex("p", "P1", 1, 1);
    model.component("comp1").geom("geom1").feature("cb2").setIndex("p", "P1", 0, 2);
    model.component("comp1").geom("geom1").feature("cb2").setIndex("p", "kk*P1", 1, 2);
    model.component("comp1").geom("geom1").feature("cb2").setIndex("p", "P1", 0, 3);
    model.component("comp1").geom("geom1").run("cb2");
    model.component("comp1").geom("geom1").create("cb3", "CubicBezier");
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "P1", 1, 0);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "-20e-6", 0, 1);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "P1", 1, 1);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "-40e-6", 0, 2);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "P2", 1, 2);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "-RL", 0, 3);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("p", "r1", 1, 3);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("w", "P3", 0);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("w", "P4", 1);
    model.component("comp1").geom("geom1").feature("cb3").setIndex("w", "P5", 2);
    model.component("comp1").geom("geom1").run("cb3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "-RL -L -L 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "r1 r1 r2 r2");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("ccur1").selection("input")
         .set("cb1", "cb2", "cb3", "ls1", "pol1");
    model.component("comp1").geom("geom1").run("ccur1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("ccur1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("csol1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-IL", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "r1", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "-IL", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "r2", 1);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("pt3", "Point");
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", "-IL", 0);
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", "-r2", 1);
    model.component("comp1").geom("geom1").run("pt3");
    model.component("comp1").geom("geom1").create("pt4", "Point");
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", "-IL", 0);
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", "-r1", 1);
    model.component("comp1").geom("geom1").run("pt4");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2e-5", "r2-r1"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-4.5e-4", "-r2"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6c34");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"1e-3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"er_w"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(1);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().set(4);
    model.component("comp1").physics("ec").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(1, 4);
    model.component("comp1").physics("spf").feature("wallbc1").set("BoundaryCondition", "ElectroosmoticVelocity");
    model.component("comp1").physics("spf").feature("wallbc1").set("E_src", "root.comp1.ec.tEx");
    model.component("comp1").physics("spf").feature("wallbc1").set("ElectroosmoticOption", "BuiltinExpression");
    model.component("comp1").physics("spf").feature("wallbc1").set("zeta", "zeta");
    model.component("comp1").physics("spf").feature("wallbc1").set("epsilonr", "er_w");
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cini", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(4);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cini", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(1);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u5185\u66f2\u7ebf");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(8, 9, 13, 16, 18, 19, 21, 22);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u5916\u66f2\u7ebf");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(7, 10, 12, 15, 20, 23);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().set(3);
    model.component("comp1").cpl("aveop1").label("\u68c0\u6d4b\u5668\u8868\u9762");

    model.component("comp1").func().create("gp1", "GaussianPulse");
    model.component("comp1").func("gp1").set("location", "xm");
    model.component("comp1").func("gp1").set("sigma", "sigma");
    model.component("comp1").func("gp1").set("normalization", "peak");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("cini", "c0*gp1(x[1/m])*(y>0)");
    model.component("comp1").variable("var1").descr("cini", "\u6d53\u5ea6\u9ad8\u65af\u8109\u51b2");
    model.component("comp1").variable("var1").set("t_in", "intop1(1/spf.U)");
    model.component("comp1").variable("var1")
         .descr("t_in", "\u6cbf\u5185\u66f2\u7ebf\u82b1\u8d39\u7684\u65f6\u95f4");
    model.component("comp1").variable("var1").set("t_out", "intop2(1/spf.U)");
    model.component("comp1").variable("var1")
         .descr("t_out", "\u6cbf\u5916\u66f2\u7ebf\u82b1\u8d39\u7684\u65f6\u95f4");
    model.component("comp1").variable("var1").set("c_avg", "aveop1(c)");
    model.component("comp1").variable("var1")
         .descr("c_avg", "\u68c0\u6d4b\u5668\u8868\u9762\u7684\u5e73\u5747\u6d53\u5ea6");

    model.study("std1").label("\u539f\u59cb\u5f2f\u66f2\u901a\u9053\u7814\u7a76");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,3.5)");
    model.study("std1").feature("time").setSolveFor("/physics/ec", false);
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6\u548c\u7535\u52bf\uff08\u539f\u59cb\u901a\u9053\uff09");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "spf.U");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("number", 30);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6\uff08\u539f\u59cb\u901a\u9053\uff09");
    model.result("pg2").setIndex("looplevel", 26, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "c");
    model.result("pg2").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").label("\u5f62\u72b6\u4f18\u5316\u7814\u7a76");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "bobyqa");
    model.study("std2").feature("opt").setIndex("optobj", "abs(comp1.t_out-comp1.t_in)", 0);
    model.study("std2").feature("opt").setIndex("pname", "c0", 0);
    model.study("std2").feature("opt").setIndex("initval", "1[mol/m^3]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "c0", 0);
    model.study("std2").feature("opt").setIndex("initval", "1[mol/m^3]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "D", 1);
    model.study("std2").feature("opt").setIndex("initval", "1e-11[m^2/s]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "D", 1);
    model.study("std2").feature("opt").setIndex("initval", "1e-11[m^2/s]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "E_av", 2);
    model.study("std2").feature("opt").setIndex("initval", "V0/L_av", 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "E_av", 2);
    model.study("std2").feature("opt").setIndex("initval", "V0/L_av", 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "er_w", 3);
    model.study("std2").feature("opt").setIndex("initval", 80, 3);
    model.study("std2").feature("opt").setIndex("scale", 1, 3);
    model.study("std2").feature("opt").setIndex("lbound", "", 3);
    model.study("std2").feature("opt").setIndex("ubound", "", 3);
    model.study("std2").feature("opt").setIndex("pname", "er_w", 3);
    model.study("std2").feature("opt").setIndex("initval", 80, 3);
    model.study("std2").feature("opt").setIndex("scale", 1, 3);
    model.study("std2").feature("opt").setIndex("lbound", "", 3);
    model.study("std2").feature("opt").setIndex("ubound", "", 3);
    model.study("std2").feature("opt").setIndex("pname", "ew", 4);
    model.study("std2").feature("opt").setIndex("initval", "er_w*epsilon0_const", 4);
    model.study("std2").feature("opt").setIndex("scale", 1, 4);
    model.study("std2").feature("opt").setIndex("lbound", "", 4);
    model.study("std2").feature("opt").setIndex("ubound", "", 4);
    model.study("std2").feature("opt").setIndex("pname", "ew", 4);
    model.study("std2").feature("opt").setIndex("initval", "er_w*epsilon0_const", 4);
    model.study("std2").feature("opt").setIndex("scale", 1, 4);
    model.study("std2").feature("opt").setIndex("lbound", "", 4);
    model.study("std2").feature("opt").setIndex("ubound", "", 4);
    model.study("std2").feature("opt").setIndex("pname", "P1", 0);
    model.study("std2").feature("opt").setIndex("scale", "50[um]", 0);
    model.study("std2").feature("opt").setIndex("lbound", "50[um]", 0);
    model.study("std2").feature("opt").setIndex("ubound", "130[um]", 0);
    model.study("std2").feature("opt").setIndex("pname", "P2", 1);
    model.study("std2").feature("opt").setIndex("scale", "50[um]", 1);
    model.study("std2").feature("opt").setIndex("lbound", "50[um]", 1);
    model.study("std2").feature("opt").setIndex("ubound", "130[um]", 1);
    model.study("std2").feature("opt").setIndex("pname", "P3", 2);
    model.study("std2").feature("opt").setIndex("scale", 0.5, 2);
    model.study("std2").feature("opt").setIndex("lbound", 0.01, 2);
    model.study("std2").feature("opt").setIndex("ubound", 1, 2);
    model.study("std2").feature("opt").setIndex("pname", "P4", 3);
    model.study("std2").feature("opt").setIndex("scale", 0.5, 3);
    model.study("std2").feature("opt").setIndex("lbound", 0.01, 3);
    model.study("std2").feature("opt").setIndex("ubound", 1, 3);
    model.study("std2").feature("opt").setIndex("pname", "P5", 4);
    model.study("std2").feature("opt").setIndex("scale", 0.5, 4);
    model.study("std2").feature("opt").setIndex("lbound", 0.01, 4);
    model.study("std2").feature("opt").setIndex("ubound", 1, 4);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std2");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("o1").run("compute");

    model.study("std2").feature("opt").set("probewindow", "");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u901f\u5ea6\u548c\u7535\u52bf\uff08\u4f18\u5316\u540e\u901a\u9053\uff09");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("number", 30);
    model.result("pg3").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ec", false);
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/tds", true);

    model.component("comp1").geom("geom1").run();

    model.study("std3").label("\u4f18\u5316\u540e\u901a\u9053\u9a8c\u8bc1");
    model.study("std3").feature("time").set("tlist", "range(0,0.1,3.5)");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", 0.001);
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initmethod", "sol");
    model.study("std3").feature("time").set("initstudy", "std2");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6\uff08\u4f18\u5316\u540e\u901a\u9053\uff09");
    model.result("pg4").set("data", "dset5");
    model.result("pg4").setIndex("looplevel", 28, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "c");
    model.result("pg4").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u68c0\u6d4b\u5668\u4e2d\u7684\u5e73\u5747\u6d53\u5ea6");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "c_avg", 0);
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u539f\u59cb\u8bbe\u8ba1", 0);
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("data", "dset5");
    model.result("pg5").feature("glob2").set("titletype", "none");
    model.result("pg5").feature("glob2").setIndex("legends", "\u4f18\u5316\u8bbe\u8ba1", 0);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result().export().create("anim1", "Animation");
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
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("fontsize", "9");
    model.result().export("anim2").set("colortheme", "globaltheme");
    model.result().export("anim2").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim2").set("background", "color");
    model.result().export("anim2").set("gltfincludelines", "on");
    model.result().export("anim2").set("title1d", "on");
    model.result().export("anim2").set("legend1d", "on");
    model.result().export("anim2").set("logo1d", "on");
    model.result().export("anim2").set("options1d", "on");
    model.result().export("anim2").set("title2d", "on");
    model.result().export("anim2").set("legend2d", "on");
    model.result().export("anim2").set("logo2d", "on");
    model.result().export("anim2").set("options2d", "off");
    model.result().export("anim2").set("title3d", "on");
    model.result().export("anim2").set("legend3d", "on");
    model.result().export("anim2").set("logo3d", "on");
    model.result().export("anim2").set("options3d", "off");
    model.result().export("anim2").set("axisorientation", "on");
    model.result().export("anim2").set("grid", "on");
    model.result().export("anim2").set("axes1d", "on");
    model.result().export("anim2").set("axes2d", "on");
    model.result().export("anim2").set("showgrid", "on");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("plotgroup", "pg4");
    model.result().export("anim2").run();
    model.result("pg3").run();

    model
         .title("\u4f18\u5316\u901a\u8fc7\u5f2f\u66f2\u5fae\u901a\u9053\u7684\u7535\u6e17\u6d41\u7684\u5e26\u5206\u6563");

    model
         .description("\u5f53\u5206\u6790\u7269\u5e26\u4ee5\u7535\u6e17\u9a71\u52a8\u6d41\u7684\u65b9\u5f0f\u6d41\u7ecf\u5f2f\u66f2\u901a\u9053\u65f6\uff0c\u7531\u4e8e\u901a\u9053\u4e2d\u5b58\u5728\u6d41\u901f\u68af\u5ea6\uff0c\u4ece\u800c\u4ea7\u751f\u5e26\u5206\u6563\u3002\u8fd9\u4e9b\u901f\u5ea6\u68af\u5ea6\u662f\u7531\u4f5c\u7528\u5728\u5f2f\u66f2\u901a\u9053\u5185\u58c1\u4e0a\u7684\u7535\u573a\u5dee\u5f02\u5f15\u8d77\u7684\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f18\u5316\u901a\u9053\u4e2d\u7684\u901f\u5ea6\uff0c\u4ee5\u6700\u5927\u7a0b\u5ea6\u51cf\u5c11\u5206\u6790\u7269\u5e26\u7684\u5206\u6563\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("microchannel_dispersion_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
