/*
 * schrodinger_poisson_nanowire.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:53 by COMSOL 6.3.0.290. */
public class schrodinger_poisson_nanowire {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Quantum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("schr", "SchrodingerEquation", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.component("comp1").multiphysics().create("schrp1", "SchrodingerPoissonCoupling", 1);
    model.component("comp1").multiphysics("schrp1").set("Schr_physics", "schr");
    model.component("comp1").multiphysics("schrp1").set("ES_physics", "es");
    model.component("comp1").multiphysics("schrp1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/schr", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/schrp1", true);

    model.component("comp1").geom("geom1").lengthUnit("nm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R0", "50[nm]", "\u7eb3\u7c73\u7ebf\u534a\u5f84");
    model.param().set("m", "0", "\u89d2\u91cf\u5b50\u6570");
    model.param().set("meff", "0.067*me_const", "\u7837\u5316\u9553\u7684\u7535\u5b50\u6709\u6548\u8d28\u91cf");
    model.param().set("epsr", "12.9", "\u7837\u5316\u9553\u7684\u4ecb\u7535\u5e38\u6570");
    model.param().set("Nd", "2*10^18[cm^-3]", "\u63ba\u6742");
    model.param().set("T", "10[K]", "\u6e29\u5ea6");
    model.param().set("kT", "k_B_const*T", "\u70ed\u80fd");
    model.param().set("beta", "1/kT", "1/kT");
    model.param()
         .set("N0TF", "1/4*(2*meff/(pi*beta*hbar_const^2))^(3/2)", "\u6709\u6548 DOS\uff0cThomas-Fermi \u8fd1\u4f3c");
    model.param().set("Ef", "0[eV]", "\u8d39\u7c73\u80fd\u7ea7");
    model.param().set("V_R0", "-(Ef+0.7[eV])/e_const", "\u7eb3\u7c73\u7ebf\u8868\u9762\u7684\u7535\u52bf");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("Ve", "-e_const*V", "\u7535\u5b50\u52bf\u80fd");
    model.component("comp1").variable("var1").set("eta", "beta*(Ef-Ve)", "\u80fd\u91cf\u56e0\u5b50");
    model.component("comp1").variable("var1")
         .set("n_TF", "N0TF*schr.FD_half(eta)", "\u7535\u5b50\u5bc6\u5ea6\uff0cThomas-Fermi \u8fd1\u4f3c");
    model.component("comp1").variable("var1")
         .set("rho_TF", "-e_const*n_TF", "\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6\uff0cThomas-Fermi \u8fd1\u4f3c");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "R0", 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("schr").prop("ModelProperties").set("m", "m");
    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psi", new String[]{"meff", "0", "0", "0", "meff", "0", "0", "0", "meff"});
    model.component("comp1").physics("schr").feature("ve1").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve1").set("Ve", 0);
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 1);
    model.component("comp1").physics("es").feature("ccnf1").selection().all();
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 0);
    model.component("comp1").physics("es").feature("pot1").selection().set(2);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V_R0");
    model.component("comp1").physics("es").feature("pot1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("es").create("scd1", "SpaceChargeDensity", 1);
    model.component("comp1").physics("es").feature("scd1")
         .label("\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6 1\uff1a\u7535\u79bb\u63ba\u6742\u5242");
    model.component("comp1").physics("es").feature("scd1").selection().all();
    model.component("comp1").physics("es").feature("scd1").set("rhoq", "e_const*Nd");
    model.component("comp1").physics("es").create("scd2", "SpaceChargeDensity", 1);
    model.component("comp1").physics("es").feature("scd2")
         .label("\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6 2\uff1aThomas Fermi");
    model.component("comp1").physics("es").feature("scd2").selection().all();
    model.component("comp1").physics("es").feature("scd2").set("rhoq", "rho_TF");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"epsr"});

    model.component("comp1").multiphysics("schrp1").set("minput_temperature", "T");
    model.component("comp1").multiphysics("schrp1").set("Ef", "Ef");
    model.component("comp1").multiphysics("schrp1").set("md", "meff");
    model.component("comp1").multiphysics("schrp1").set("gi", "1+(m>0)");
    model.component("comp1").multiphysics("schrp1").set("alpha1", 4);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 200);

    model.study("std1").label("\u7814\u7a76 1\uff1aThomas-Fermi");
    model.study("std1").feature("stat").setSolveFor("/physics/schr", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/schrp1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("solutionparams", "parent");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("sdpo", "SchrodingerPoisson");
    model.study("std2").feature("sdpo").set("plotgroup", "Default");
    model.study("std2").feature("sdpo").set("ftplistmethod", "manual");
    model.study("std2").feature("sdpo").set("neigs", "3");
    model.study("std2").feature("sdpo").set("eigunit", "");
    model.study("std2").feature("sdpo").set("appnreigs", "4");
    model.study("std2").feature("sdpo").set("maxnreigs", "6");
    model.study("std2").feature("sdpo").set("eigsr", "-0.1");
    model.study("std2").feature("sdpo").set("eiglr", "0.1");
    model.study("std2").feature("sdpo").set("eigsi", "-1.0E-7");
    model.study("std2").feature("sdpo").set("eigli", "1.0E-7");
    model.study("std2").feature("sdpo").set("linpsolnum", "auto");
    model.study("std2").feature("sdpo").set("method", "minimization_of_global_variable");
    model.study("std2").feature("sdpo").set("expr", "schrp1.global_err");
    model.study("std2").feature("sdpo").set("atolterm", "1.0E-6");
    model.study("std2").feature("sdpo").set("solnum", "auto");
    model.study("std2").feature("sdpo").set("notsolnum", "auto");
    model.study("std2").feature("sdpo").set("outputmap", new String[]{});
    model.study("std2").feature("sdpo").set("ngenAUX", "1");
    model.study("std2").feature("sdpo").set("goalngenAUX", "1");
    model.study("std2").feature("sdpo").set("ngenAUX", "1");
    model.study("std2").feature("sdpo").set("goalngenAUX", "1");
    model.study("std2").feature("sdpo").setSolveFor("/physics/schr", true);
    model.study("std2").feature("sdpo").setSolveFor("/physics/es", true);
    model.study("std2").feature("sdpo").setSolveFor("/multiphysics/schrp1", true);
    model.study("std2").feature("sdpo").set("eigmethod", "region");
    model.study("std2").feature("sdpo").set("eigsr", -0.15);
    model.study("std2").feature("sdpo").set("eiglr", 0.05);
    model.study("std2").feature("sdpo").set("useadvanceddisable", true);
    model.study("std2").feature("sdpo").set("disabledphysics", new String[]{"es/scd2"});
    model.study("std2").feature("sdpo").set("useinitsol", true);
    model.study("std2").feature("sdpo").set("initmethod", "sol");
    model.study("std2").feature("sdpo").set("initstudy", "std1");
    model.study("std2").feature("sdpo").set("useparam", true);
    model.study("std2").feature("sdpo").setIndex("pname", "R0", 0);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 0);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 0);
    model.study("std2").feature("sdpo").setIndex("pname", "R0", 0);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 0);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 0);
    model.study("std2").feature("sdpo").setIndex("pname", "m", 0);
    model.study("std2").feature("sdpo").setIndex("plistarr", "0 1 2 3 4 5 6", 0);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u859b\u5b9a\u8c14-\u6cca\u677e");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u6982\u7387\u5bc6\u5ea6 (schr)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("expr", "schr.Pr");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "R");
    model.result("pg2").feature("lngr1").set("smooth", "internal");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u52bf\u80fd (schr)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("expr", "schr.V_psi");
    model.result("pg3").feature("lngr1").set("unit", "eV");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "R");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u6709\u6548\u8d28\u91cf (schr)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("expr", "schr.meff_psiRR/me_const");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "R");
    model.result("pg4").feature("lngr1").set("smooth", "everywhere");
    model.result("pg4").feature("lngr1").set("data", "parent");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u7535\u52bf (es) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").feature().create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("solutionparams", "parent");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "r");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("data", "parent");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6")
         .label("\u5c06 n \u548c V \u4e0e\u4e0a\u6b21\u8fed\u4ee3\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83 (schrp1)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u5c06 n \u548c V \u4e0e\u4e0a\u6b21\u8fed\u4ee3\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg6").set("ylabel", "\u7c92\u5b50\u5bc6\u5ea6");
    model.result("pg6").set("showsecylabel", "on");
    model.result("pg6").set("yseclabel", "\u52bf\u80fd (eV)");
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").feature().create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").label("\u52bf\u80fd");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("plotonsecyaxis", true);
    model.result("pg6").feature("lngr1").set("expr", "schrp1.zq*schrp1.V");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "R");
    model.result("pg6").feature("lngr1").set("smooth", "internal");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("data", "parent");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature().create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").label("\u4e0a\u6b21\u8fed\u4ee3\u5f97\u5230\u7684\u52bf\u80fd");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("plotonsecyaxis", true);
    model.result("pg6").feature("lngr2").set("expr", "schrp1.zq*schrp1.V_old");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", "R");
    model.result("pg6").feature("lngr2").set("smooth", "internal");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("data", "parent");
    model.result("pg6").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr2").selection().set(1);
    model.result("pg6").feature().create("lngr3", "LineGraph");
    model.result("pg6").feature("lngr3")
         .label("\u6839\u636e\u52a0\u6743\u6c42\u548c\u5f97\u5230\u7684\u7c92\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr3").set("expr", "schrp1.n_sum");
    model.result("pg6").feature("lngr3").set("xdata", "expr");
    model.result("pg6").feature("lngr3").set("xdataexpr", "R");
    model.result("pg6").feature("lngr3").set("linestyle", "dashed");
    model.result("pg6").feature("lngr3").set("linecolor", "black");
    model.result("pg6").feature("lngr3").set("smooth", "internal");
    model.result("pg6").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr3").set("data", "parent");
    model.result("pg6").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr3").selection().set(1);
    model.result("pg6").feature().create("lngr4", "LineGraph");
    model.result("pg6").feature("lngr4").label("\u7c92\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr4").set("expr", "schrp1.rhoq/schrp1.q");
    model.result("pg6").feature("lngr4").set("xdata", "expr");
    model.result("pg6").feature("lngr4").set("xdataexpr", "R");
    model.result("pg6").feature("lngr4").set("smooth", "internal");
    model.result("pg6").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr4").set("data", "parent");
    model.result("pg6").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr4").selection().set(1);
    model.result("pg6").feature().create("lngr5", "LineGraph");
    model.result("pg6").feature("lngr5").label("\u4e0a\u6b21\u8fed\u4ee3\u5f97\u5230\u7684\u7c92\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr5").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr5").set("expr", "schrp1.rhoq_old/schrp1.q");
    model.result("pg6").feature("lngr5").set("xdata", "expr");
    model.result("pg6").feature("lngr5").set("xdataexpr", "R");
    model.result("pg6").feature("lngr5").set("smooth", "internal");
    model.result("pg6").feature("lngr5").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr5").set("data", "parent");
    model.result("pg6").feature("lngr5").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr5").selection().set(1);
    model.result("pg2").run();
    model.result("pg2").set("data", "dset4");
    model.result("pg6").run();
    model.result().move("pg6", 4);
    model.result().move("pg6", 3);
    model.result().move("pg6", 2);
    model.result().move("pg6", 1);
    model.result("pg6")
         .label("\u5c06 V \u548c n \u4e0e Thomas-Fermi \u8fd1\u4f3c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg6")
         .set("title", "\u5c06 V \u548c n \u4e0e Thomas-Fermi \u8fd1\u4f3c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg6").set("ylabel", "\u7535\u5b50\u5bc6\u5ea6 / Nd");
    model.result("pg6").set("yseclabel", "\u7535\u5b50\u52bf\u80fd (eV)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").label("\u7535\u5b50\u52bf\u80fd");
    model.result("pg6").feature("lngr1").set("descractive", true);
    model.result("pg6").feature("lngr1").set("descr", "\u7535\u5b50\u52bf\u80fd");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").label("\u4e0a\u6b21\u8fed\u4ee3\u5f97\u5230\u7684\u7535\u5b50\u52bf\u80fd");
    model.result("pg6").feature("lngr2").set("descractive", true);
    model.result("pg6").feature("lngr2").set("descr", "\u7535\u5b50\u52bf\u80fd\uff0c\u4e0a\u6b21\u8fed\u4ee3");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").label("\u52a0\u6743\u548c\u5f97\u5230\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr3").set("expr", "schrp1.n_sum/Nd");
    model.result("pg6").feature("lngr3").set("descractive", true);
    model.result("pg6").feature("lngr3").set("descr", "\u7535\u5b50\u5bc6\u5ea6\uff0c\u52a0\u6743\u548c");
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").label("\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr4").set("expr", "schrp1.rhoq/schrp1.q/Nd");
    model.result("pg6").feature("lngr4").set("descractive", true);
    model.result("pg6").feature("lngr4").set("descr", "\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").run();
    model.result("pg6").feature("lngr5").label("\u4e0a\u6b21\u8fed\u4ee3\u5f97\u5230\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr5").set("expr", "schrp1.rhoq_old/schrp1.q/Nd");
    model.result("pg6").feature("lngr5").set("descractive", true);
    model.result("pg6").feature("lngr5").set("descr", "\u7535\u5b50\u5bc6\u5ea6\uff0c\u4e0a\u6b21\u8fed\u4ee3");
    model.result("pg6").feature().duplicate("lngr6", "lngr5");
    model.result("pg6").run();
    model.result("pg6").feature("lngr6")
         .label("Thomas-Fermi \u8fd1\u4f3c\u5f97\u5230\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr6").set("data", "dset1");
    model.result("pg6").feature("lngr6").set("expr", "n_TF/Nd");
    model.result("pg6").feature("lngr6").set("descr", "\u7535\u5b50\u5bc6\u5ea6\uff0cThomas-Fermi");
    model.result("pg6").feature("lngr6").set("linestyle", "dotted");
    model.result("pg6").feature("lngr6").set("linecolor", "black");
    model.result("pg6").feature().duplicate("lngr7", "lngr6");
    model.result("pg6").run();
    model.result("pg6").feature("lngr7")
         .label("Thomas-Fermi \u8fd1\u4f3c\u5f97\u5230\u7684\u7535\u5b50\u52bf\u80fd");
    model.result("pg6").feature("lngr7").set("plotonsecyaxis", true);
    model.result("pg6").feature("lngr7").set("expr", "-V");
    model.result("pg6").feature("lngr7").set("descr", "\u7535\u5b50\u52bf\u80fd\uff0cThomas-Fermi");
    model.result("pg6").run();
    model.result("pg6").create("ann1", "Annotation");
    model.result("pg6").feature("ann1").set("plotonsecyaxis", true);
    model.result("pg6").feature("ann1").set("text", "$\\longrightarrow$");
    model.result("pg6").feature("ann1").set("posxexpr", 46);
    model.result("pg6").feature("ann1").set("posyexpr", 0.47);
    model.result("pg6").feature("ann1").set("showpoint", false);
    model.result("pg6").feature("ann1").set("latexmarkup", true);

    model.sol("sol2").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol2").feature("s1").feature("fc1").set("plotgroup", "pg6");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result().move("pg7", 5);
    model.result().move("pg7", 4);
    model.result().move("pg7", 3);
    model.result().move("pg7", 2);
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 16);
    model.result("pg7").set("ymin", 0.9);
    model.result("pg7").set("ymax", 1.002);
    model.result("pg7").set("yminsec", "-86.5e-3");
    model.result("pg7").set("ymaxsec", "-80.5e-3");
    model.result("pg7").run();
    model.result("pg7").feature("ann1").set("posxexpr", 14.5);
    model.result("pg7").feature("ann1").set("posyexpr", -0.0827);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr8", "lngr6");
    model.result("pg6").run();
    model.result("pg6").feature("lngr8").label("m=0 \u65f6\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr8")
         .set("expr", "sum(withsol('sol4',schrp1.ni,setval(m,0),setind(lambda,jj)),jj,1,4)/Nd");
    model.result("pg6").feature("lngr8").set("descr", "m=0");
    model.result("pg6").feature("lngr8").set("linestyle", "dashdot");
    model.result("pg6").feature("lngr8").set("linecolor", "cycle");
    model.result("pg6").feature().duplicate("lngr9", "lngr8");
    model.result("pg6").run();
    model.result("pg6").feature("lngr9").label("m=+/-1 \u65f6\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr9")
         .set("expr", "sum(withsol('sol4',schrp1.ni,setval(m,1),setind(lambda,jj)),jj,1,4)/Nd");
    model.result("pg6").feature("lngr9").set("descr", "|m|=1");
    model.result("pg6").feature().duplicate("lngr10", "lngr9");
    model.result("pg6").run();
    model.result("pg6").feature("lngr10").label("m=+/-2 \u65f6\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr10")
         .set("expr", "sum(withsol('sol4',schrp1.ni,setval(m,2),setind(lambda,jj)),jj,1,4)/Nd");
    model.result("pg6").feature("lngr10").set("descr", "|m|=2");
    model.result("pg6").feature().duplicate("lngr11", "lngr10");
    model.result("pg6").run();
    model.result("pg6").feature("lngr11").label("m=+/-3 \u65f6\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr11")
         .set("expr", "sum(withsol('sol4',schrp1.ni,setval(m,3),setind(lambda,jj)),jj,1,3)/Nd");
    model.result("pg6").feature("lngr11").set("descr", "|m|=3");
    model.result("pg6").feature().duplicate("lngr12", "lngr11");
    model.result("pg6").run();
    model.result("pg6").feature("lngr12").label("m=+/-4 \u65f6\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr12")
         .set("expr", "sum(withsol('sol4',schrp1.ni,setval(m,4),setind(lambda,jj)),jj,1,3)/Nd");
    model.result("pg6").feature("lngr12").set("descr", "|m|=4");
    model.result("pg6").feature().duplicate("lngr13", "lngr12");
    model.result("pg6").run();
    model.result("pg6").feature("lngr13").label("m=+/-5 \u65f6\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").feature("lngr13")
         .set("expr", "sum(withsol('sol4',schrp1.ni,setval(m,5),setind(lambda,jj)),jj,1,2)/Nd");
    model.result("pg6").feature("lngr13").set("descr", "|m|=5");
    model.result("pg6").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u70b9\u8ba1\u7b97 1\uff1a\u68c0\u67e5\u7535\u8377\u4e2d\u6027");
    model.result().numerical("pev1").set("data", "dset2");
    model.result().numerical("pev1").selection().set(2);
    model.result().numerical("pev1")
         .setIndex("expr", "(schrp1.int(2*pi*R*schrp1.n_sum)-2*pi*R*es.nD/e_const)/(pi*R^2*Nd)-1", 0);
    model.result().numerical("pev1").setIndex("descr", "\u76f8\u5bf9\u8bef\u5dee", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1\uff1a\u68c0\u67e5\u7535\u8377\u4e2d\u6027");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();

    model.title("\u7837\u5316\u9553\u7eb3\u7c73\u7ebf\u7684\u81ea\u6d3d\u859b\u5b9a\u8c14-\u6cca\u677e\u7ed3\u679c");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u91c7\u7528\u81ea\u6d3d\u859b\u5b9a\u8c14-\u6cca\u677e\u7406\u8bba\u6a21\u62df\u7837\u5316\u9553\u7eb3\u7c73\u7ebf\uff0c\u4ee5\u8ba1\u7b97\u7535\u5b50\u5bc6\u5ea6\u548c\u9650\u5236\u52bf\u5206\u5e03\u3002\u9884\u5b9a\u4e49\u7684\u201c\u859b\u5b9a\u8c14-\u6cca\u677e\u201d\u591a\u7269\u7406\u573a\u8026\u5408\u7279\u5f81\u4e0e\u4e13\u7528\u7684\u201c\u859b\u5b9a\u8c14-\u6cca\u677e\u201d\u7814\u7a76\u7c7b\u578b\u76f8\u7ed3\u5408\uff0c\u4e3a\u6a21\u578b\u8bbe\u7f6e\u548c\u81ea\u52a8\u521b\u5efa\u53c2\u6570\u53ef\u8c03\u7684\u81ea\u6d3d\u8fed\u4ee3\u63d0\u4f9b\u7b80\u5316\u7a0b\u5e8f\uff0c\u4ece\u800c\u4f18\u5316\u4e0d\u540c\u6761\u4ef6\u4e0b\u7684\u6536\u655b\u6027\u3002\u8ba1\u7b97\u51fa\u7684\u7535\u5b50\u5bc6\u5ea6\u548c\u9650\u5236\u52bf\u5206\u5e03\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7684\u6570\u636e\u9ad8\u5ea6\u543b\u5408\uff0c\u8fd9\u4e24\u4e2a\u5206\u5e03\u56fe\u90fd\u53ef\u4ee5\u91cd\u73b0\u4f4e\u6e29\u4e0b\u660e\u663e\u7684 Friedel \u578b\u7a7a\u95f4\u632f\u8361\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("schrodinger_poisson_nanowire.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
