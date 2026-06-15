/*
 * li_air_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:18 by COMSOL 6.3.0.290. */
public class li_air_battery_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");
    model.component("comp1").physics("liion").field("concentration").field("cLi");
    model.component("comp1").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cO2");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cO2"});
    model.component("comp1").physics().create("dode", "DomainODE", "geom1");
    model.component("comp1").physics("dode").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("dode").prop("Units").set("DependentVariableQuantity", "concentration");
    model.component("comp1").physics("dode").field("dimensionless").component(new String[]{"ecLi2O2"});
    model.component("comp1").physics("dode").prop("Units").set("SourceTermQuantity", "reactionrate");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/dode", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Lsep", "5e-5[m]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("Lpos", "7.5e-4[m]", "\u6b63\u6781\u957f\u5ea6");
    model.param().set("Kpos", "10[S/m]", "\u6b63\u6781\u7535\u5bfc\u7387");
    model.param().set("epsilonl0", "0.73", "\u6b63\u6781\u7684\u521d\u59cb\u5b54\u9699\u7387");
    model.param()
         .set("epsilons0", "1-epsilonl0", "\u6b63\u6781\u7684\u521d\u59cb\u6d3b\u6027\u6750\u6599\u56fa\u4f53\u5206\u6570");
    model.param().set("epsilonsep", "0.87", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("rpos0", "25e-9[m]", "\u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param()
         .set("apos0", "3*epsilons0/rpos0", "\u6b63\u6781\u7684\u521d\u59cb\u6d3b\u6027\u6bd4\u8868\u9762\u79ef");
    model.param().set("Rfilm", "50[ohm*m^2]", "Li2O2 \u819c\u7684\u819c\u963b");
    model.param().set("cLi0", "1000[mol/m^3]", "\u7535\u89e3\u8d28\u4e2d\u7684\u521d\u59cb Li+ \u6d53\u5ea6");
    model.param().set("SolO2", "0.40", "\u7535\u89e3\u8d28\u4e2d\u6c27\u7684\u6eb6\u89e3\u5ea6\u56e0\u5b50");
    model.param()
         .set("cO2ext", "9.46[mol/m^3]", "1 \u4e2a\u5927\u6c14\u538b\u4e0b\u5916\u90e8\u7a7a\u6c14\u4e2d\u7684\u6c27\u6d53\u5ea6");
    model.param().set("cO20", "SolO2*cO2ext", "\u6b63\u6781\u7684\u521d\u59cb\u6c27\u6d53\u5ea6");
    model.param()
         .set("cmaxLi2O2", "0.09[mol/m^3]", "\u7535\u89e3\u8d28\u4e2d\u6eb6\u89e3\u7684 Li2O2 \u7684\u6eb6\u89e3\u6781\u9650");
    model.param().set("DLi", "2.11e-9[m^2/s]", "\u7535\u89e3\u8d28\u4e2d Li+ \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DO2", "7e-10[m^2/s]", "\u6c27\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("kappaLi", "1.085[S/m]", "\u7535\u89e3\u8d28\u4e2d Li+ \u7684\u7535\u5bfc\u7387");
    model.param().set("tplus", "0.2594", "\u7535\u89e3\u8d28\u4e2d Li+ \u7684\u8fc1\u79fb\u6570");
    model.param().set("dlnfdlnc", "-1.03", "\u6d3b\u6027\u76f8\u5173\u6027");
    model.param().set("ka", "1.11e-15[m/s]", "\u9633\u6781\u7535\u6d41\u7684\u53cd\u5e94\u901f\u7387\u7cfb\u6570");
    model.param()
         .set("kc", "3.4e-17[m^7/s/mol^2]", "\u9634\u6781\u7535\u6d41\u7684\u53cd\u5e94\u901f\u7387\u7cfb\u6570");
    model.param()
         .set("i0refLi", "9.65e-1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9502\u91d1\u5c5e");
    model.param().set("Eeq", "2.96[V]", "\u6c27\u8fd8\u539f\u53cd\u5e94\u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("n", "2", "\u8f6c\u79fb\u7535\u5b50\u6570");
    model.param().set("rhoLi2O2", "2140[kg/m^3]", "Li2O2 \u7684\u5bc6\u5ea6");
    model.param().set("MLi2O2", "45.88e-3[kg/mol]", "Li2O2 \u7684\u5206\u5b50\u91cf");
    model.param().set("rhocarbon", "2260[kg/m^3]", "\u78b3\u7684\u5bc6\u5ea6");
    model.param().set("T", "300[K]", "\u6e29\u5ea6");
    model.param().set("i_app", "-0.05*a[mA/cm^2]", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("a", "1", "\u7528\u4e8e\u53c2\u6570\u5316\u626b\u63cf");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "Lsep", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "Lpos", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(2);

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("epsilonLi2O2", "liion.deltaepstot_pce1", "Li2O2 \u7684\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("capacity", "abs(i_app)*t/(epsilons0*Lpos*rhocarbon*3600)", "\u7535\u6c60\u7684\u6bd4\u5bb9\u91cf");
    model.component("comp1").variable("var1")
         .set("cLi2O2", "ecLi2O2/epsilonl0", "\u57fa\u4e8e\u5b54\u9699\u4e2d\u7684\u6eb6\u6db2\u4f53\u79ef\u7684 Li2O2 \u6d53\u5ea6");
    model.component("comp1").variable("var1")
         .set("apos", "apos0*(1-max((epsilonLi2O2/epsilonl0),eps)^0.5)", "\u6b63\u6781\u7684\u6d3b\u6027\u6bd4\u8868\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("CRpos", "ka*min(cLi2O2,cmaxLi2O2)*(n*F_const)/(1[A/m^2])", "\u6b63\u6781\u7684\u8fd8\u539f\u7269\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1")
         .set("COpos", "kc*cLi^2*cO2*(n*F_const)/(1[A/m^2])", "\u6b63\u6781\u7684\u6c27\u5316\u7269\u8868\u8fbe\u5f0f");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "EndTerminal");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);

    model.component("comp1").physics("liion").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1")
         .set("sigmal", new String[]{"kappaLi", "0", "0", "0", "kappaLi", "0", "0", "0", "kappaLi"});
    model.component("comp1").physics("liion").feature("sep1").set("Dl_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("Dl", "DLi");
    model.component("comp1").physics("liion").feature("sep1").set("transpNum_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("transpNum", "tplus");
    model.component("comp1").physics("liion").feature("sep1").set("fcl_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fcl", "dlnfdlnc");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsilonsep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().set(2);
    model.component("comp1").physics("liion").feature("pce1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigmal", new String[]{"kappaLi", "0", "0", "0", "kappaLi", "0", "0", "0", "kappaLi"});
    model.component("comp1").physics("liion").feature("pce1").set("Dl_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("Dl", "DLi");
    model.component("comp1").physics("liion").feature("pce1").set("transpNum_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("transpNum", "tplus");
    model.component("comp1").physics("liion").feature("pce1").set("fcl_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("fcl", "dlnfdlnc");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"Kpos", "0", "0", "0", "Kpos", "0", "0", "0", "Kpos"});
    model.component("comp1").physics("liion").feature("pce1")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epsilons0");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsilonl0");
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", "rhoLi2O2", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", "MLi2O2", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").set("AddVolumeChangeToElectrodeVolumeFraction", false);
    model.component("comp1").physics("liion").feature("pce1").set("FilmResistanceType", "SurfaceResistance");
    model.component("comp1").physics("liion").feature("pce1").set("Rfilm", "Rfilm*epsilonLi2O2");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("Eeq", "Eeq");
    model.component("comp1").physics("liion").feature("pce1").feature("per1")
         .set("ElectrodeKinetics", "ConcentrationDependentKinetics");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0", 1);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("alphaa", "0.5*n");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("alphac", "0.5*n");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("CR", "CRpos");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("CO", "COpos");
    model.component("comp1").physics("liion").feature("pce1").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("Av", "apos");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("nm", "n");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("VLiPlus", -2);
    model.component("comp1").physics("liion").feature("pce1").feature("per1")
         .setIndex("Vib", "1*(cLi2O2>=cmaxLi2O2)", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("liion").feature("es1").selection().set(1);
    model.component("comp1").physics("liion").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("es1").feature("er1").set("i0_ref", "i0refLi");
    model.component("comp1").physics("liion").feature("es1").feature("er1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(3);
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "i_app");
    model.component("comp1").physics("liion").feature("init1").set("cLi", "cLi0");
    model.component("comp1").physics("liion").feature("init1").set("phis", "Eeq");
    model.component("comp1").physics("liion").create("init2", "init", 1);
    model.component("comp1").physics("liion").feature("init2").selection().set(1);
    model.component("comp1").physics("liion").feature("init2").set("cLi", "cLi0");
    model.component("comp1").physics("tds").selection().set(2);
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", 2);
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cO2", new String[]{"DO2", "0", "0", "0", "DO2", "0", "0", "0", "DO2"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("FluidDiffusivityModelType", "BruggemanModel");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro", "liion.epsl");
    model.component("comp1").physics("tds").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tds").feature("conc1").selection().set(3);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cO20", 0);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cO20", 0);
    model.component("comp1").physics("tds").create("pec1", "PorousElectrodeCoupling", 1);
    model.component("comp1").physics("tds").feature("pec1").selection().set(2);
    model.component("comp1").physics("tds").feature("pec1").feature("rc1")
         .set("iv_src", "root.comp1.liion.pce1.per1.iv");
    model.component("comp1").physics("tds").feature("pec1").feature("rc1").set("nm", "n");
    model.component("comp1").physics("tds").feature("pec1").feature("rc1").setIndex("Vi", -1, 0);
    model.component("comp1").physics("dode")
         .label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b\uff1aLi2O2 \u7684\u6d53\u5ea6");
    model.component("comp1").physics("dode").selection().set(2);
    model.component("comp1").physics("dode").prop("ShapeProperty").set("shapeFunctionType", "shlag");
    model.component("comp1").physics("dode").feature("dode1")
         .setIndex("f", "((-liion.ivtot*1)/(2*F_const))*(cLi2O2<cmaxLi2O2)", 0);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Lsep", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Lsep", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2 4 10", 0);
    model.study("std1").feature("time").set("tlist", "0 1e7");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-4");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").set("tstepsstore", 3);
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.EndTerminal(comp1.phis)<2.5", 0);
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u4e0d\u540c iapp \u7684\u7535\u6c60\u7535\u538b");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(3);
    model.result("pg1").feature("ptgr1").set("expr", "phis");
    model.result("pg1").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "capacity");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u7535\u6c60\u7684\u6bd4\u5bb9\u91cf");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg1").feature("ptgr1").set("legendpattern", "eval(-i_app,mA/cm^2) mA/cm^2");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u7535\u6c60\u7684\u6bd4\u5bb9 (mAh/g)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u7535\u6c60\u7535\u538b");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6b63\u6781\u7684\u6c27\u6c14\u6d53\u5ea6\uff0ciapp = 0.1mA/cm2");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(2);
    model.result("pg2").feature("lngr1").set("expr", "cO2");
    model.result("pg2").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccO2");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x/(Lsep+Lpos)");
    model.result("pg2").feature("lngr1").set("xdatadescractive", true);
    model.result("pg2").feature("lngr1")
         .set("xdatadescr", "\u6b63\u6781\u7535\u6781\u7684\u65e0\u91cf\u7eb2\u539a\u5ea6");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6b63\u6781\u7684\u6c27\u6c14\u6d53\u5ea6");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u6b63\u6781\u7684\u6c27\u6c14\u6d53\u5ea6\uff0ciapp = 0.5mA/cm2");
    model.result("pg3").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6b63\u6781\u4e2d Li2O2 \u7684\u4f53\u79ef\u5206\u6570\uff0ciapp = 0.1mA/cm2");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg4").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").setIndex("interp", "1 2e5 5e5 1e6 1.15e6", 0);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(2);
    model.result("pg4").feature("lngr1").set("expr", "epsilonLi2O2");
    model.result("pg4").feature("lngr1").set("descr", "Li2O2 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x/(Lsep+Lpos)");
    model.result("pg4").feature("lngr1").set("xdatadescractive", true);
    model.result("pg4").feature("lngr1")
         .set("xdatadescr", "\u6b63\u6781\u7535\u6781\u7684\u65e0\u91cf\u7eb2\u539a\u5ea6");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6b63\u6781\u4e2d Li2O2 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymax", 0.6);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6b63\u6781\u4e2d Li2O2 \u7684\u4f53\u79ef\u5206\u6570\uff0ciapp = 0.5mA/cm2");
    model.result("pg5").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg5").setIndex("interp", "1 5e3 2e4 4e4", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6b63\u6781\u7684\u5b54\u9699\u7387\uff0ciapp = 0.1mA/cm2");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg6").setIndex("looplevelinput", "interp", 0);
    model.result("pg6").setIndex("interp", "1 2e5 5e5 1e6 1.15e6", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(2);
    model.result("pg6").feature("lngr1").set("expr", "liion.epsl");
    model.result("pg6").feature("lngr1").set("descractive", true);
    model.result("pg6").feature("lngr1").set("descr", "\u5b54\u9699\u7387");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x/(Lsep+Lpos)");
    model.result("pg6").feature("lngr1").set("xdatadescractive", true);
    model.result("pg6").feature("lngr1")
         .set("xdatadescr", "\u6b63\u6781\u7535\u6781\u7684\u65e0\u91cf\u7eb2\u539a\u5ea6");
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6b63\u6781\u7684\u5b54\u9699\u7387");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("ymin", 0.15);
    model.result("pg6").set("ymax", 0.75);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6b63\u6781\u7684\u5b54\u9699\u7387\uff0ciapp = 0.5mA/cm2");
    model.result("pg7").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg7").setIndex("interp", "1 5e3 2e4 4e4", 0);
    model.result("pg7").run();

    model.title("\u9502\u7a7a\u6c14\u7535\u6c60\u7b49\u6e29\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u9502\u79bb\u5b50\u7535\u6c60\u201d\u63a5\u53e3\u6765\u7814\u7a76\u9502\u7a7a\u6c14\u7535\u6c60\u7684\u653e\u7535\uff0c\u901a\u8fc7\u201c\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u7a00\u7269\u8d28\u4f20\u9012\u201d\u63a5\u53e3\u5bf9\u6c27\u5728\u591a\u5b54\u6b63\u6781\u4e2d\u7684\u4f20\u9012\u8fdb\u884c\u5efa\u6a21\u3002\u6b63\u6781\u4e2d\u6c27\u8fd8\u539f\u7684\u7535\u5316\u5b66\u53cd\u5e94\u5bfc\u81f4\u53cd\u5e94\u4ea7\u7269\u6d53\u5ea6\u548c\u7535\u6781\u5b54\u9699\u7387\u53d1\u751f\u53d8\u5316\u3002\u5176\u4e2d\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u7ed3\u6784\u548c\u7b49\u6e29\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("li_air_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
