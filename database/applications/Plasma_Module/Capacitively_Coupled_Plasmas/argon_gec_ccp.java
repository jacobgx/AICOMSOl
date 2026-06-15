/*
 * argon_gec_ccp.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:00 by COMSOL 6.3.0.290. */
public class argon_gec_ccp {

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

    model.param().set("L", "2.54[cm]");
    model.param().descr("L", "\u653e\u7535\u95f4\u9699");
    model.param().set("R1", "5.38[cm]");
    model.param().descr("R1", "\u5185\u534a\u5f84");
    model.param().set("R2", "10.16[cm]");
    model.param().descr("R2", "\u5916\u534a\u5f84");
    model.param().set("Hd", "10.16[cm]");
    model.param().descr("Hd", "\u8154\u9ad8\u5ea6");
    model.param().set("dThick", "3[mm]");
    model.param().descr("dThick", "\u4ecb\u7535\u5c42\u539a\u5ea6");
    model.param().set("f0", "13.56[MHz]");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("P0", "1[W]");
    model.param().descr("P0", "\u529f\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R1", "L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R2-R1", "Hd"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"R1", "-Hd/2+L/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "R1-dThick", 0);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "L", 1);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").feature().duplicate("pt3", "pt1");
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", "R2", 0);
    model.component("comp1").geom("geom1").run("pt3");
    model.component("comp1").geom("geom1").feature().duplicate("pt4", "pt2");
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", "R2", 0);
    model.component("comp1").geom("geom1").run("pt4");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pt2", 1);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("pt1", 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").set("includevtx", false);
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4, 9);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.component("comp1").selection("sel1").label("\u58c1");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection("sel2").label("\u9a71\u52a8\u7535\u6781");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(4, 6);
    model.component("comp1").selection("sel3").label("\u4ecb\u7535\u63a5\u89e6");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(3, 5, 7, 8, 9, 10, 11, 12);
    model.component("comp1").selection("sel4").label("\u63a5\u5730\u58c1");

    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Period_xd", "1/f0");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Nelemptp", 30);
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings")
         .set("HeavySpeciesSelection", "BaseGeometry");
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "Ar_xsecs_reduced.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("Ars").active(false);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("ptp").feature("Ar_1p")
         .set("IonTemperatureSpecification", "LocalFieldApproximation");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("MobilitySpecification", "ArIoninAr");
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("gammaf", 0);
    model.component("comp1").physics("ptp").feature("pes1").set("T", "300[K]");
    model.component("comp1").physics("ptp").feature("pes1").set("pA", "0.1[torr]");
    model.component("comp1").physics("ptp").feature("pes1").set("SpecifyElectronDensityAndEnergy", "SpecifyMueOnly");
    model.component("comp1").physics("ptp").feature("pes1")
         .set("mue", new String[]{"3E5[cm^2/(V*s)]/0.1", "0", "0", "0", "3E5[cm^2/(V*s)]/0.1", "0", "0", "0", "3E5[cm^2/(V*s)]/0.1"});
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("ptp").feature("wall1").selection().named("sel1");
    model.component("comp1").physics("ptp").feature("wall1").set("re", "5/11");
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 1);
    model.component("comp1").physics("ptp").feature("mct1").selection().named("sel2");
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "P0");
    model.component("comp1").physics("ptp").feature("mct1").set("fp", "f0");
    model.component("comp1").physics("ptp").feature("mct1").set("CompBias", true);
    model.component("comp1").physics("ptp").create("dct1", "DielectricContact", 1);
    model.component("comp1").physics("ptp").feature("dct1").selection().named("sel3");
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ptp").feature("gnd1").selection().named("sel4");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(7, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(6, 8, 10, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(1, 11, 13, 14);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u65f6\u95f4\u5468\u671f\u7814\u7a76");
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

    model.sol("sol1").feature("s1").feature("fc1").set("plot", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u79bb\u6e90\uff0c\u5e73\u5747\u5468\u671f");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "ptp.Re_av");
    model.result("pg6").feature("surf1")
         .set("descr", "\u901f\u7387\u8868\u8fbe\u5f0f\uff0c\u5468\u671f\u5e73\u5747");
    model.result("pg6").feature("surf1").set("unit", "1/(cm^3*s)");
    model.result("pg6").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "2[cm]", 1, 1);
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "cln1");
    model.result("pg7").label("\u8f74\u4e0a\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").run();
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", "L/2", 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "R2", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "L/2", 1, 1);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "cln2");
    model.result("pg8").label("\u5f84\u5411\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").run();
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
    model.result("pg8").run();

    model.study().create("std2");
    model.study("std2").create("tptd", "TimePeriodicToTimeDependent");
    model.study("std2").feature("tptd").set("plotgroup", "Default");
    model.study("std2").feature("tptd").set("ftplistmethod", "manual");
    model.study("std2").feature("tptd").set("initialtime", "0");
    model.study("std2").feature("tptd").set("solnum", "auto");
    model.study("std2").feature("tptd").set("notsolnum", "auto");
    model.study("std2").feature("tptd").set("outputmap", new String[]{});
    model.study("std2").feature("tptd").setSolveFor("/physics/ptp", true);
    model.study("std2").feature("tptd").set("tlist", "range(0,(1/f0-0)/50,1/f0)");
    model.study("std2").feature("tptd").set("usesol", true);
    model.study("std2").feature("tptd").set("notsolmethod", "sol");
    model.study("std2").feature("tptd").set("notstudy", "std1");
    model.study("std2").label("\u65f6\u95f4\u5468\u671f\u5230\u77ac\u6001");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 51, 0);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"ptp.ne"});
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 51, 0);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"ptp.Te"});
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 51, 0);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg11").feature("surf1").set("colortable", "Dipole");
    model.result("pg9").label("\u7535\u5b50\u5bc6\u5ea6 (ptp)");
    model.result("pg10").label("\u7535\u5b50\u6e29\u5ea6 (ptp)");
    model.result("pg11").label("\u7535\u52bf (ptp)");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("unit", new String[]{""});
    model.result("pg12").feature("glob1").set("expr", new String[]{"ptp.mct1.V"});
    model.result("pg12").feature("glob1").set("descr", new String[]{"\u7535\u52bf"});
    model.result("pg12").feature("glob1").set("legend", true);
    model.result("pg12").feature("glob1").set("legendmethod", "manual");
    model.result("pg12").feature("glob1").setIndex("legends", "V", 0);
    model.result("pg12").create("glob2", "Global");
    model.result("pg12").feature("glob2").set("unit", new String[]{""});
    model.result("pg12").feature("glob2").set("expr", new String[]{"ptp.mct1.I"});
    model.result("pg12").feature("glob2").set("descr", new String[]{"\u7535\u6d41"});
    model.result("pg12").feature("glob2").set("legend", true);
    model.result("pg12").feature("glob2").set("legendmethod", "manual");
    model.result("pg12").feature("glob2").setIndex("legends", "I", 0);
    model.result("pg12").set("twoyaxes", true);
    model.result("pg12").setIndex("plotonsecyaxis", true, 0, 1);
    model.result("pg12").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp) 1");
    model.result("pg9").run();
    model.result("pg11").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg11");
    model.result().export("anim1").run();
    model.result("pg1").run();

    model.title("GEC CCP \u53cd\u5e94\u5668\uff0c\u6c29\u5316\u5b66");

    model
         .description("\u672c\u6a21\u578b\u4f7f\u7528\u201c\u7b49\u79bb\u5b50\u4f53\uff0c\u65f6\u95f4\u5468\u671f\u201d\u63a5\u53e3\u5728\u4e8c\u7ef4\u4e0b\u7814\u7a76 GEC\uff08\u6c14\u4f53\u7535\u5b50\u4f1a\u8bae\uff09\u53c2\u8003\u7535\u6c60\uff0c\u8be5\u7535\u6c60\u7531\u989d\u5b9a\u529f\u7387\u9a71\u52a8\uff0c\u7814\u7a76\u7ed3\u679c\u4e0e\u6587\u732e\u53d1\u8868\u7684\u7ed3\u679c\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("argon_gec_ccp.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
