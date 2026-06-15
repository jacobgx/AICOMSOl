/*
 * ccp_ion_energy_distribution_function.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:01 by COMSOL 6.3.0.290. */
public class ccp_ion_energy_distribution_function {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Capacitively_Coupled_Plasmas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ptp", "ColdPlasmaTimePeriodic", "geom1");

    model.study().create("std1");
    model.study("std1").create("tper", "TimePeriodic");
    model.study("std1").feature("tper").set("ftplistmethod", "manual");
    model.study("std1").feature("tper").set("solnum", "auto");
    model.study("std1").feature("tper").set("notsolnum", "auto");
    model.study("std1").feature("tper").set("outputmap", new String[]{});
    model.study("std1").feature("tper").set("ngenAUX", "1");
    model.study("std1").feature("tper").set("ngen", "2");
    model.study("std1").feature("tper").set("goalngenAUX", "1");
    model.study("std1").feature("tper").set("ngenAUX", "1");
    model.study("std1").feature("tper").set("ngen", "2");
    model.study("std1").feature("tper").set("goalngenAUX", "1");
    model.study("std1").feature("tper").setSolveFor("/physics/ptp", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().set("r0", "160[mm]");
    model.param().set("h0", "4.5[cm]");
    model.param().set("r1", "110[mm]");
    model.param().set("h1", "40[mm]");
    model.param().set("Prf", "1[W]");
    model.param().set("f0", "13.56[MHz]");
    model.param().set("p0", "0.02[torr]");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r0", "h0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"r0-r1", "h1"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"r1", "-h1"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("cha1").set("dist", 10);
    model.component("comp1").geom("geom1").run("cha1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("cha1", 3, 5);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 6);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 97.5, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "h0", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("fil1", 3);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("pt1", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Period_xd", "1/f0");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Nelemptp", 30);
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings")
         .set("HeavySpeciesSelection", "BaseGeometry");
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("ptp").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("ptp").feature("rxn1").set("kf", "2.3E7");
    model.component("comp1").physics("ptp").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("ptp").feature("rxn2").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("ptp").feature("rxn2").set("kf", 1807);
    model.component("comp1").physics("ptp").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("ptp").feature("Ar_1p")
         .set("IonTemperatureSpecification", "LocalFieldApproximation");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("MobilitySpecification", "ArIoninAr");
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("gammaf", 0);
    model.component("comp1").physics("ptp").feature("sr1").selection().set(2, 3);
    model.component("comp1").physics("ptp").feature().duplicate("sr2", "sr1");
    model.component("comp1").physics("ptp").feature("sr2").selection().set(4, 5, 6, 7, 8, 9);
    model.component("comp1").physics("ptp").feature("sr2").set("gammai", 0);
    model.component("comp1").physics("ptp").feature("sr2").set("ebari", 0);
    model.component("comp1").physics("ptp").feature().duplicate("sr3", "sr1");
    model.component("comp1").physics("ptp").feature("sr3").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr3").set("gammaf", 1);
    model.component("comp1").physics("ptp").feature().duplicate("sr4", "sr2");
    model.component("comp1").physics("ptp").feature("sr4").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr4").set("gammaf", 1);
    model.component("comp1").physics("ptp").feature("pes1").set("T", "300[K]");
    model.component("comp1").physics("ptp").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("ptp").feature("wall1").selection().all();
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(3);
    model.component("comp1").physics("ptp").create("dct1", "DielectricContact", 1);
    model.component("comp1").physics("ptp").feature("dct1").selection().set(4, 5, 6, 7, 8, 9);
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 1);
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "Prf");
    model.component("comp1").physics("ptp").feature("mct1").set("fp", "f0");
    model.component("comp1").physics("ptp").feature("mct1").set("CompBias", true);
    model.component("comp1").physics("ptp").feature("mct1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 12);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(3, 10);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"ptp.neav"});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"ptp.Teav"});
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"ptp.Vav"});
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"ptp.Pcap_av"});
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg4").label("\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg3").label("\u7535\u52bf\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").set("solution", "sol1");
    model.result().dataset("dset2").set("geom", "ptp_xdim");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", new String[]{"x1_ptp"});
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", new String[]{"ptp.mct1.V"});
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "V", 0);
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", new String[]{"x1_ptp"});
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("expr", new String[]{"ptp.mct1.I"});
    model.result("pg5").feature("lngr2").set("resolution", "norefine");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").setIndex("legends", "I", 0);
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 0, 1);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "Period fraction");
    model.result("pg5").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp)");
    model.result("pg1").run();

    model.study("std1").feature("tper").set("useparam", true);
    model.study("std1").feature("tper").setIndex("pname", "r0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "m", 0);
    model.study("std1").feature("tper").setIndex("pname", "r0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "m", 0);
    model.study("std1").feature("tper").setIndex("pname", "Prf", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "1 30", 0);

    model.sol("sol1").feature("s1").feature("fc1").set("rstepabs", 0.03);
    model.sol("sol1").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol1").feature("s1").feature("pDef").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("pDef").set("pinitstep", 0.25);
    model.sol("sol1").feature("s1").feature("pDef").set("pmaxstep", 0.5);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("tptd", "TimePeriodicToTimeDependent");
    model.study("std2").feature("tptd").set("plotgroup", "Default");
    model.study("std2").feature("tptd").set("ftplistmethod", "manual");
    model.study("std2").feature("tptd").set("initialtime", "0");
    model.study("std2").feature("tptd").set("solnum", "auto");
    model.study("std2").feature("tptd").set("notsolnum", "auto");
    model.study("std2").feature("tptd").set("outputmap", new String[]{});
    model.study("std2").feature("tptd").setSolveFor("/physics/ptp", true);
    model.study("std2").feature("tptd").set("tlist", "range(0,(1/f0)/101,1/f0)");
    model.study("std2").feature("tptd").set("usesol", true);
    model.study("std2").feature("tptd").set("notsolmethod", "sol");
    model.study("std2").feature("tptd").set("notstudy", "std1");
    model.study("std2").feature("tptd").set("notsolnum", "last");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 102, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"ptp.ne"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 102, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"ptp.Te"});
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 102, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").label("\u7535\u5b50\u5bc6\u5ea6 (ptp)");
    model.result("pg7").label("\u7535\u5b50\u6e29\u5ea6 (ptp)");
    model.result("pg8").label("\u7535\u52bf (ptp)");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("unit", new String[]{""});
    model.result("pg9").feature("glob1").set("expr", new String[]{"ptp.mct1.V"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"\u7535\u52bf"});
    model.result("pg9").feature("glob1").set("legend", true);
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1").setIndex("legends", "V", 0);
    model.result("pg9").create("glob2", "Global");
    model.result("pg9").feature("glob2").set("unit", new String[]{""});
    model.result("pg9").feature("glob2").set("expr", new String[]{"ptp.mct1.I"});
    model.result("pg9").feature("glob2").set("descr", new String[]{"\u7535\u6d41"});
    model.result("pg9").feature("glob2").set("legend", true);
    model.result("pg9").feature("glob2").set("legendmethod", "manual");
    model.result("pg9").feature("glob2").setIndex("legends", "I", 0);
    model.result("pg9").set("twoyaxes", true);
    model.result("pg9").setIndex("plotonsecyaxis", true, 0, 1);
    model.result("pg9").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp) 1");
    model.result("pg6").run();

    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study("std1").feature("tper").setSolveFor("/physics/cpt", false);
    model.study("std2").feature("tptd").setSolveFor("/physics/cpt", false);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "(7*1e-10-0.6*1e-10*log(x))^2");
    model.component("comp1").func("an1").setIndex("argunit", "eV", 0);
    model.component("comp1").func("an1").set("fununit", "m^2");
    model.component("comp1").func("an1").set("funcname", "Qex");
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("expr", "2e-19/(x^(0.5)*(1+x))+3e-19*x/(1+x/3)^(2.3)");
    model.component("comp1").func("an2").setIndex("argunit", "eV", 0);
    model.component("comp1").func("an2").set("fununit", "m^2");
    model.component("comp1").func("an2").set("funcname", "Qele");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Mion", "0.04[kg/mol]/N_A_const");

    model.component("comp1").physics("cpt").feature("pp1").set("mp", "Mion");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 2);
    model.component("comp1").physics("cpt").feature("ef1").selection().set(1);
    model.component("comp1").physics("cpt").feature("ef1").set("SpecifyForceUsing", "ElectricPotential");
    model.component("comp1").physics("cpt").feature("ef1").set("V_src", "root.comp1.V");
    model.component("comp1").physics("cpt").feature("ef1").set("TimeDependenceOfField", "Periodic");
    model.component("comp1").physics("cpt").feature("ef1").set("UsePPR", true);
    model.component("comp1").physics("cpt").create("col1", "Collisions", 2);
    model.component("comp1").physics("cpt").feature("col1").set("Nd", "ptp.Nn");
    model.component("comp1").physics("cpt").feature("col1").set("T", "300[K]");
    model.component("comp1").physics("cpt").feature("col1").selection().set(1);
    model.component("comp1").physics("cpt").feature("col1").create("cex1", "ResonantChargeExchange", 2);
    model.component("comp1").physics("cpt").feature("col1").feature("cex1").set("xsec", "Qex(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col1").feature("cex1").set("chis", "5[deg]");
    model.component("comp1").physics("cpt").feature("col1").create("ela1", "Elastic", 2);
    model.component("comp1").physics("cpt").feature("col1").feature("ela1").set("xsec", "Qele(cpt.Ep)");
    model.component("comp1").physics("cpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("rt", "range(0,(1/f0)/30,1/f0)", 0);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", 40, 0);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", 8, 1);
    model.component("comp1").physics("cpt").feature("relg1").set("VelocitySpecification", "Maxwellian");
    model.component("comp1").physics("cpt").feature("relg1").set("SamplingFromDistribution", "Random");
    model.component("comp1").physics("cpt").feature("relg1").setIndex("M", 30, 0);
    model.component("comp1").physics("cpt").feature("relg1").set("T0", "300[K]");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ptp", false);
    model.study("std3").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std3").feature("time").set("tlist", "range(0,(1/f0),(1/f0)*40)");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol3").feature("t1").set("maxstepgenalpha", "1E-9");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qr", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "part1");
    model.result("pg10").setIndex("looplevel", 41, 0);
    model.result("pg10").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg10").create("traj1", "ParticleTrajectories");
    model.result("pg10").feature("traj1").set("pointtype", "point");
    model.result("pg10").feature("traj1").set("linetype", "none");
    model.result("pg10").feature("traj1").create("col1", "Color");
    model.result("pg10").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u79bb\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (IEDF)");
    model.result("pg11").set("data", "part1");
    model.result("pg11").setIndex("looplevelinput", "last", 0);
    model.result("pg11").create("hist1", "Histogram");
    model.result("pg11").feature("hist1").set("markerpos", "datapoints");
    model.result("pg11").feature("hist1").set("linewidth", "preference");
    model.result("pg11").feature("hist1").set("expr", "cpt.Ep");
    model.result("pg11").feature("hist1").set("unit", "eV");
    model.result("pg11").feature("hist1").set("method", "limits");
    model.result("pg11").feature("hist1").set("limits", "range(0,2,400)");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").run();
    model.result("pg12").label("\u79bb\u5b50\u89d2\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (IAEDF)");
    model.result("pg12").set("data", "part1");
    model.result("pg12").stepLast(0);
    model.result("pg12").run();
    model.result("pg12").create("hist1", "Histogram");
    model.result("pg12").feature("hist1").set("xexpr", "atan(cpt.vr/cpt.vz)");
    model.result("pg12").feature("hist1").set("xunit", "\u00b0");
    model.result("pg12").feature("hist1").set("xdescractive", true);
    model.result("pg12").feature("hist1").set("xdescr", "\u89d2");
    model.result("pg12").feature("hist1").set("yexpr", "cpt.Ep");
    model.result("pg12").feature("hist1").set("yunit", "eV");
    model.result("pg12").feature("hist1").set("xmethod", "limits");
    model.result("pg12").feature("hist1").set("xlimits", "range(-10,0.40816326530612246,10)");
    model.result("pg12").feature("hist1").set("ymethod", "limits");
    model.result("pg12").feature("hist1").set("ylimits", "range(0,2,400)");
    model.result("pg12").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"ptp.mct1.Va_per"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7535\u538b\u5e45\u503c"});
    model.result().numerical("gev1").set("unit", new String[]{"V"});
    model.result().numerical("gev1").set("expr", new String[]{"ptp.mct1.Va_per", "ptp.mct1.Vdcb_per"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u7535\u538b\u5e45\u503c", "\u76f4\u6d41\u504f\u538b"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg11").run();
    model.result("pg12").run();

    model
         .title("\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u4e2d\u7684\u79bb\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570");

    model
         .description("\u672c\u6a21\u578b\u8ba1\u7b97\u5546\u7528\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u7684\u79bb\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (IEDF)\uff0c\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u6570\u636e\u9ad8\u5ea6\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("ccp_ion_energy_distribution_function.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
