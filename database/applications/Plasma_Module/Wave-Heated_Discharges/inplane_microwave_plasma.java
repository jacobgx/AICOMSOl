/*
 * inplane_microwave_plasma.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:08 by COMSOL 6.3.0.290. */
public class inplane_microwave_plasma {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Wave-Heated_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");
    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");
    model.component("comp1").physics("emw").prop("ShapeProperty").set("order_electricfield", "1");

    model.component("comp1").multiphysics().create("pcc1", "PlasmaConductivityMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("pcc1").set("elm_physics", "emw");
    model.component("comp1").multiphysics("pcc1").set("plas_physics", "plas");
    model.component("comp1").multiphysics().create("ehs1", "ElectronHeatSourceMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("ehs1").set("elm_physics", "emw");
    model.component("comp1").multiphysics("ehs1").set("plas_physics", "plas");

    model.study().create("std1");
    model.study("std1").create("ftrans", "FrequencyTransient");
    model.study("std1").feature("ftrans").set("initialtime", "0");
    model.study("std1").feature("ftrans").set("freq", "1000");
    model.study("std1").feature("ftrans").set("solnum", "auto");
    model.study("std1").feature("ftrans").set("notsolnum", "auto");
    model.study("std1").feature("ftrans").set("outputmap", new String[]{});
    model.study("std1").feature("ftrans").setSolveFor("/physics/plas", true);
    model.study("std1").feature("ftrans").setSolveFor("/physics/emw", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/pcc1", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/ehs1", true);

    model.param().set("P0", "30[W]");
    model.param().descr("P0", "\u5438\u6536\u529f\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.05, 0.1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.25, 0.05});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{-0.1, 0.1});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.05, 0.1});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 0.15});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1, 2, 3, 6, 8, 11, 13);
    model.component("comp1").selection("sel1").label("\u58c1");

    model.component("comp1").physics("plas").selection().set(1);
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "3.734E8");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", 1807);
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr2").selection().named("sel1");
    model.component("comp1").physics("plas").prop("TransportSettings").set("Convection", true);
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").feature("pes1").set("u", new int[]{10, 0, 0});
    model.component("comp1").physics("plas").feature("pes1").set("T", 350);
    model.component("comp1").physics("plas").feature("pes1").set("pA", "1[torr]");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[]{"4E24", "0", "0", "0", "4E24", "0", "0", "0", "4E24"});
    model.component("comp1").physics("plas").feature("init1").set("neinit", "1E17");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel1");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().named("sel1");
    model.component("comp1").physics("plas").create("eout1", "ElectronOutlet", 1);
    model.component("comp1").physics("plas").feature("eout1").selection().set(14);
    model.component("comp1").physics("plas").create("out1", "Outflow", 1);
    model.component("comp1").physics("plas").feature("out1").selection().set(14);
    model.component("comp1").physics("plas").feature("out1").set("IncludeIons", true);
    model.component("comp1").physics("emw").prop("components").set("components", "outofplane");
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(9);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").feature("port1").set("SpecifyDepositedPower", true);
    model.component("comp1").physics("emw").feature("port1").set("Pdep", "P0");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2, 3);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("ftrans").set("tlist", "0 10^{range(-8,0.2,-2)}");
    model.study("std1").feature("ftrans").set("freq", "2.45[GHz]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 32, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 32, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 32, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (emw)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", 7.6E16);
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u963b\u70ed");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "emw.Qrh");
    model.result("pg5").feature("surf1").set("descr", "\u7535\u963b\u635f\u8017");
    model.result("pg5").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg5").run();

    model.component("comp1").multiphysics("pcc1").set("TensorSigma", true);
    model.component("comp1").multiphysics("pcc1").set("dopplerb", 20);

    model.component("comp1").physics("emw").prop("components").set("components", "inplane");

    model.study().create("std2");
    model.study("std2").create("ftrans", "FrequencyTransient");
    model.study("std2").feature("ftrans").set("plotgroup", "Default");
    model.study("std2").feature("ftrans").set("initialtime", "0");
    model.study("std2").feature("ftrans").set("freq", "1000");
    model.study("std2").feature("ftrans").set("solnum", "auto");
    model.study("std2").feature("ftrans").set("notsolnum", "auto");
    model.study("std2").feature("ftrans").set("outputmap", new String[]{});
    model.study("std2").feature("ftrans").setSolveFor("/physics/plas", true);
    model.study("std2").feature("ftrans").setSolveFor("/physics/emw", true);
    model.study("std2").feature("ftrans").setSolveFor("/multiphysics/pcc1", true);
    model.study("std2").feature("ftrans").setSolveFor("/multiphysics/ehs1", true);
    model.study("std2").feature("ftrans").set("tlist", "0 10^{range(-8,0.2,-2)}");
    model.study("std2").feature("ftrans").set("freq", "2.45[GHz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 32, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 32, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 32, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").label("\u7535\u5b50\u5bc6\u5ea6 (plas) 1");
    model.result("pg7").label("\u7535\u5b50\u6e29\u5ea6 (plas) 1");
    model.result("pg8").label("\u7535\u52bf (plas) 1");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u7535\u573a (emw) 1");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 32, 0);
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("expr", "emw.normE");
    model.result("pg9").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg5").run();
    model.result().duplicate("pg10", "pg5");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset2");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7aef\u53e3\u529f\u7387");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"emw.port1.Pin"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u7aef\u53e3\u8f93\u5165\u529f\u7387"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"W/m"});
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("legendmethod", "manual");
    model.result("pg11").feature("glob1").setIndex("legends", "TE \u6a21\u5f0f", 0);
    model.result("pg11").set("xlog", true);
    model.result("pg11").run();
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("glob2", "glob1");
    model.result("pg11").run();
    model.result("pg11").feature("glob2").set("data", "dset2");
    model.result("pg11").feature("glob2").setIndex("legends", "TM \u6a21\u5f0f", 0);
    model.result("pg11").feature("glob2").set("titletype", "none");
    model.result("pg11").run();

    model.title("\u9762\u5185\u5fae\u6ce2\u7b49\u79bb\u5b50\u4f53");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u8bbe\u7f6e\u4e86\u4e00\u4e2a\u4e8c\u7ef4\u6c29\u6c14\u7b49\u79bb\u5b50\u4f53\u6a21\u578b\uff0c\u901a\u8fc7\u7535\u78c1\u6ce2\u6301\u7eed\u653e\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("inplane_microwave_plasma.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
