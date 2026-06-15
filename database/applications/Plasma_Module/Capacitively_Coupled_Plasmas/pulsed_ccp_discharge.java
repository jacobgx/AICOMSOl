/*
 * pulsed_ccp_discharge.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:01 by COMSOL 6.3.0.290. */
public class pulsed_ccp_discharge {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Capacitively_Coupled_Plasmas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

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

    model.param().set("f0", "13.56[MHz]");
    model.param().descr("f0", "\u57fa\u9891");
    model.param().set("As", "0.25*pi*(0.3[m])^2");
    model.param().descr("As", "\u6676\u7247\u8868\u9762\u79ef");
    model.param().set("p0", "0.1[torr]");
    model.param().descr("p0", "\u538b\u529b");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("Prf", "1[W]");
    model.param().descr("Prf", "\u5355\u8109\u51b2\u529f\u7387");
    model.param().set("Non", "10");
    model.param().descr("Non", "\u8109\u51b2\u5f00\u542f\u7684\u5468\u671f\u6570");
    model.param().set("Noff", "100");
    model.param().descr("Noff", "\u8109\u51b2\u5173\u95ed\u7684\u5468\u671f\u6570");
    model.param().set("Nrampup", "1");
    model.param().descr("Nrampup", "\u8f6c\u4e3a\u5f00\u542f\u72b6\u6001\u7684\u5468\u671f\u6570");
    model.param().set("Nrampdown", "1");
    model.param().descr("Nrampdown", "\u8f6c\u4e3a\u5173\u95ed\u72b6\u6001\u7684\u5468\u671f\u6570");
    model.param().set("Nelon", "30");
    model.param().descr("Nelon", "\u5f00\u5468\u671f\u7684\u6bcf\u5468\u671f\u5355\u5143\u6570");
    model.param().set("Neloff", "100");
    model.param().descr("Neloff", "\u95ed\u5468\u671f\u7684\u5355\u5143\u6570");
    model.param().set("Ncycles", "Non+Noff");
    model.param().descr("Ncycles", "\u5468\u671f\u603b\u6570");
    model.param().set("period_single", "Ncycles/f0");
    model.param().descr("period_single", "\u603b\u5468\u671f");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.025, 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").create("rxn1", "Reaction", 1);
    model.component("comp1").physics("ptp").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("ptp").feature("rxn1").set("kf", "3.734E8");
    model.component("comp1").physics("ptp").feature().duplicate("rxn2", "rxn1");
    model.component("comp1").physics("ptp").feature("rxn2").set("formula", "Ar+Ars=>Ar+Ar");
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
    model.component("comp1").physics("ptp").feature("pes1").set("T", "T0");
    model.component("comp1").physics("ptp").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr1").selection().all();
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("gammaf", 0);
    model.component("comp1").physics("ptp").create("sr2", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr2").selection().all();
    model.component("comp1").physics("ptp").feature("sr2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("gammai", 0);
    model.component("comp1").physics("ptp").feature("sr2").set("ebari", 0);
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("ptp").feature("wall1").selection().all();
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 0);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(2);
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 0);
    model.component("comp1").physics("ptp").feature("mct1").selection().set(1);
    model.component("comp1").physics("ptp").feature("mct1").set("Source", "RF");
    model.component("comp1").physics("ptp").feature("mct1").set("PeriodicFunction", "Pulsed");
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "Prf");
    model.component("comp1").physics("ptp").feature("mct1").set("fp", "f0");
    model.component("comp1").physics("ptp").feature("mct1").set("CreateMeshSuggestionForExtraDimension", true);
    model.component("comp1").physics("ptp").feature("mct1").set("nperiodOn", "Non");
    model.component("comp1").physics("ptp").feature("mct1").set("nperiodOff", "Noff");
    model.component("comp1").physics("ptp").feature("mct1").set("nperiodRampUp", "Nrampup");
    model.component("comp1").physics("ptp").feature("mct1").set("nperiodRampDn", "Nrampdown");
    model.component("comp1").physics("ptp").feature("mct1").set("nelon", "Nelon");
    model.component("comp1").physics("ptp").feature("mct1").set("neloff", "Neloff");
    model.component("comp1").physics("ptp").prop("CrossSectionArea").set("A", "As");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Period_xd", "period_single");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("MeshSpecification", "mct1");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings")
         .set("HeavySpeciesSelection", "BaseGeometry");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 75);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u5355\u8109\u51b2");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"ptp.neav"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"ptp.Teav"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"ptp.Vav"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", new String[]{"x"});
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", new String[]{"ptp.Pcap_av"});
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "Electrons", 0);
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", new String[]{"x"});
    model.result("pg4").feature("lngr2").selection().all();
    model.result("pg4").feature("lngr2").set("expr", new String[]{"ptp.Pcap_ions_av"});
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "Ions", 0);
    model.result("pg4").feature("lngr2").set("resolution", "norefine");
    model.result("pg4").create("lngr3", "LineGraph");
    model.result("pg4").feature("lngr3").set("xdata", "expr");
    model.result("pg4").feature("lngr3").set("xdataexpr", new String[]{"x"});
    model.result("pg4").feature("lngr3").selection().all();
    model.result("pg4").feature("lngr3").set("expr", new String[]{"ptp.Pcap_ele_ions_av"});
    model.result("pg4").feature("lngr3").set("legend", true);
    model.result("pg4").feature("lngr3").set("legendmethod", "manual");
    model.result("pg4").feature("lngr3").setIndex("legends", "Electrons and ions", 0);
    model.result("pg4").feature("lngr3").set("resolution", "norefine");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Power Deposition (W/m<sup>3</sup>)");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").label("\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Neutral Species Number Density, Period Averaged (1/m<sup>3</sup>)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "Charged Species Number Density, Period Averaged (1/m<sup>3</sup>)");
    model.result("pg6").set("ylabel", "Number density (1/m<sup>3</sup>)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", new String[]{"x"});
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", new String[]{"ptp.n_wAr_av"});
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "wAr", 0);
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", new String[]{"x"});
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("expr", new String[]{"ptp.n_wArs_av"});
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").setIndex("legends", "wArs", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", new String[]{"x"});
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("expr", new String[]{"ptp.n_wAr_1p_av"});
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "wAr_1p", 0);
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", new String[]{"x"});
    model.result("pg6").feature("lngr2").selection().all();
    model.result("pg6").feature("lngr2").set("expr", new String[]{"ptp.neav"});
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "ne", 0);
    model.result("pg5").label("\u4e2d\u6027\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg6").label("\u5e26\u7535\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg3").label("\u7535\u52bf\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").set("solution", "sol1");
    model.result().dataset("dset2").set("geom", "ptp_xdim");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", new String[]{"x1_ptp"});
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", new String[]{"ptp.mct1.V"});
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "V", 0);
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").set("xdataexpr", new String[]{"x1_ptp"});
    model.result("pg7").feature("lngr2").selection().all();
    model.result("pg7").feature("lngr2").set("expr", new String[]{"ptp.mct1.I"});
    model.result("pg7").feature("lngr2").set("resolution", "norefine");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("legendmethod", "manual");
    model.result("pg7").feature("lngr2").setIndex("legends", "I", 0);
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7").setIndex("plotonsecyaxis", true, 0, 1);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "Period fraction");
    model.result("pg7").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp)");
    model.result("pg1").run();
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0.4);
    model.result("pg7").set("xmax", 0.6);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u7535\u5b50\u5bc6\u5ea6 vs. \u65f6\u95f4");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").selection().all();
    model.result("pg8").feature("lngr1").set("expr", "comp1.atxd1(0.0125,ptp.ne)");
    model.result("pg8").feature("lngr1").set("descractive", true);
    model.result("pg8").feature("lngr1").set("descr", "\u653e\u7535\u4e2d\u5fc3\u7684\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x1_ptp[1/m]*period_single");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("xdatadescractive", true);
    model.result("pg8").feature("lngr1").set("xdatadescr", "\u65f6\u95f4");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u5b50\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "comp1.atxd1(0.0125,ptp.Te)");
    model.result("pg9").feature("lngr1").set("descr", "\u653e\u7535\u4e2d\u5fc3\u7684\u7535\u5b50\u6e29\u5ea6");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u7535\u52bf vs. \u65f6\u95f4");
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("expr", "comp1.atxd1(0.0125,ptp.V)");
    model.result("pg10").feature("lngr1").set("descr", "\u653e\u7535\u4e2d\u5fc3\u7684\u7535\u52bf");
    model.result("pg10").run();

    model.title("\u8109\u51b2 CCP \u653e\u7535");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u8109\u51b2\u7535\u5bb9\u653e\u7535\u6a21\u578b\u3002\u5bfc\u901a\u8109\u51b2\u5305\u542b 13.56\u00a0MHz \u4e0b\u7684 10\u00a0\u4e2a\u6fc0\u52b1\u5468\u671f\uff0c\u4ee5\u53ca\u540e\u7eed 100\u00a0\u4e2a\u65e0\u6fc0\u52b1\u5468\u671f\u3002\u672c\u4f8b\u7814\u7a76 I-V \u7279\u6027\u3001\u7535\u5b50\u5bc6\u5ea6\u3001\u6e29\u5ea6\u548c\u7b49\u79bb\u5b50\u4f53\u7535\u4f4d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pulsed_ccp_discharge.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
