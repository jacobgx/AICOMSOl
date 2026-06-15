/*
 * charge_exchange_cell_simulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:16 by COMSOL 6.3.0.290. */
public class charge_exchange_cell_simulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("M_gas", "40[g/mol]", "Molar mass: neutral gas (Ar)");
    model.param().set("M_p", "1[g/mol]", "Molar mass: particles (H+)");
    model.param().set("E_p", "1[keV]", "Energy: particles (H+)");
    model.param().set("N0", "1000", "Number of particles");
    model.param().set("E1", "2.16[eV]", "Energy loss collision 1");
    model.param().set("E2", "13.6[eV]", "Energy loss collision 2");
    model.param().set("E3", "15.6[eV]", "Energy loss collision 3");
    model.param().set("Rin", "2[mm]", "Aperture radius (Rin)");
    model.param().set("Rcell", "20[mm]", "Cell inner radius (Rcell)");
    model.param().set("Lcell", "100[mm]", "Cell length (Lcell)");
    model.param().set("wthcell", "1.5[mm]", "Cell wall thickness");
    model.param().set("owth", "4[mm]", "Outgasing wall thickness");
    model.param().set("Low", "10[mm]", "Outgasing wall length (Low)");
    model.param().set("Rvac", "32[mm]", "Vacuum chamber inner radius (Rvac)");
    model.param().set("Lvac", "200[mm]", "Vacuum chamber length (Lvac)");
    model.param().set("wdp", "5*Rin", "Width deflection plates");
    model.param().set("Ldp", "30[mm]", "Length deflection plate (Ldp)");
    model.param().set("thdp", "wthcell", "Thickness deflection plate");
    model.param().set("ddp", "8[mm]", "Distance between deflection plates (Ddp)");
    model.param().set("T0", "293.15[K]", "Surface temperature");
    model.param().set("sccm", "0.05", "Outgasing rate in sccm units");
    model.param().set("pspeed", "63[l/s]", "Pump speed");
    model.param().set("V0", "200[V]", "Electric potential upper plate");
    model.param().set("beta", "1[m]", "Beam Twiss parameter");
    model.param().set("erms", "0.1[um]", "Beam rms emittance");
    model.param().set("Emp", "1[keV]", "Most probable particle energy");
    model.param().set("I0", "1[nA]", "Beam initial current");
    model.param().set("tmax", "1.1*Lvac/sqrt(Emp/(1/2*M_p/N_A_const))", "Simulation time");
    model.param().set("nstep", "50", "Minimum number of time steps");
    model.param().set("nsec", "1000", "Maximum number of secondary particles");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").repairTolType("relative");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"owth", "Low"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"Rcell+wthcell-owth", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "-Low/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"wthcell", "Lcell"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"Rcell", "-Lcell/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"Rcell+wthcell-Rin", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("size", "wthcell", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"Rin", "Lcell/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"Rcell+wthcell-Rin", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("size", "wthcell", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"Rin", "-Lcell/2-wthcell"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "r2", "r3", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Rvac");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lvac");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "-Lvac/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "Rvac");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Lvac/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "-Lvac/2"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"wdp", "Ldp", "thdp"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "3*Lvac/8", "ddp/2+thdp/2"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"wdp", "Ldp", "thdp"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"0", "3*Lvac/8", "-(ddp/2+thdp/2)"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1", "blk2", "rev1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", -100);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "Rin");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("Ar+H+=>H+Ar+");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "charge_exchange_cell_simulator_embedded_Qex1.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "Qex1");
    model.component("comp1").func("int1").setIndex("argunit", "eV", 0);
    model.component("comp1").func("int1").setIndex("fununit", "m^2", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("H+Ar=>Ar+H+");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "charge_exchange_cell_simulator_embedded_Qex2.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("funcname", "Qex2");
    model.component("comp1").func("int2").setIndex("argunit", "eV", 0);
    model.component("comp1").func("int2").setIndex("fununit", "m^2", 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").label("H+Ar=>H+Ar+");
    model.component("comp1").func("int3").set("source", "file");
    model.component("comp1").func("int3").set("filename", "charge_exchange_cell_simulator_embedded_Qex3.txt");
    model.component("comp1").func("int3").importData();
    model.component("comp1").func("int3").set("funcname", "Qex3");
    model.component("comp1").func("int3").setIndex("argunit", "eV", 0);
    model.component("comp1").func("int3").setIndex("fununit", "m^2", 0);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Nc1", "cpt.sum(if(isnan(qx),0,cpt.col1.ncex1.Nc))");
    model.component("comp1").variable("var1").descr("Nc1", "Number of collisions, type 1");
    model.component("comp1").variable("var1").set("Nc2", "cpt.sum(if(isnan(qx),0,cpt.col2.ncex1.Nc))");
    model.component("comp1").variable("var1").descr("Nc2", "Number of collisions, type 2");
    model.component("comp1").variable("var1").set("Nc3", "cpt.sum(if(isnan(qx),0,cpt.col3.ncex1.Nc))");
    model.component("comp1").variable("var1").descr("Nc3", "Number of collisions, type 3");
    model.component("comp1").variable("var1").set("Nctot", "Nc1+Nc2+Nc3");
    model.component("comp1").variable("var1").descr("Nctot", "Total number of collisions");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmax", 0);
    model.component("comp1").selection("box1").set("condition", "allvertices");

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "M_gas", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "T0");
    model.component("comp1").physics("fmf").create("wall2", "Wall", 2);
    model.component("comp1").physics("fmf").feature("wall2").selection().set(35, 36, 69, 71);
    model.component("comp1").physics("fmf").feature("wall2").set("BCType", "OutgassingWall");
    model.component("comp1").physics("fmf").feature("wall2").set("BoundaryCondition", "NumberOfSCCM");
    model.component("comp1").physics("fmf").feature("wall2").setIndex("sccmmfr", "sccm", 0);
    model.component("comp1").physics("fmf").feature("wall2").set("StandardFlowRateDefinedBy", "StandardP");
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp1").selection().set(6);
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp1").setIndex("pspeed", "pspeed", 0);
    model.component("comp1").physics("fmf").create("ndr1", "NumberDensityReconDomain", 3);
    model.component("comp1").physics("fmf").feature("ndr1").selection().set(1);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(41, 42, 43, 44, 46, 90);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89);
    model.component("comp1").physics("cpt").prop("MaximumSecondary").setIndex("MaximumSecondary", "nsec", 0);
    model.component("comp1").physics("cpt").prop("StoreParticleStatusData")
         .setIndex("StoreParticleStatusData", 1, 0);
    model.component("comp1").physics("cpt").feature("pp1").label("H+");
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "M_p/N_A_const");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("pp2", "ParticlePropertiesOther", -1);
    model.component("comp1").physics("cpt").feature("pp2").label("H");
    model.component("comp1").physics("cpt").feature("pp2").set("mp", "M_p/N_A_const");
    model.component("comp1").physics("cpt").feature("pp2").set("Z", 0);
    model.component("comp1").physics("cpt").create("pp3", "ParticlePropertiesOther", -1);
    model.component("comp1").physics("cpt").feature("pp3").label("Ar+");
    model.component("comp1").physics("cpt").feature("pp3").set("mp", "M_gas/N_A_const");
    model.component("comp1").physics("cpt").feature("pp3").set("Z", 1);
    model.component("comp1").physics("cpt").create("pbeam1", "ParticleBeam", 2);
    model.component("comp1").physics("cpt").feature("pbeam1").selection().set(47);
    model.component("comp1").physics("cpt").feature("pbeam1").setIndex("N", "N0", 0);
    model.component("comp1").physics("cpt").feature("pbeam1").set("twbeta0", "beta");
    model.component("comp1").physics("cpt").feature("pbeam1").set("e1rms", "erms");
    model.component("comp1").physics("cpt").feature("pbeam1").set("El", "Emp");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().set(1);
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").create("col1", "Collisions", 3);
    model.component("comp1").physics("cpt").feature("col1").label("Ar+H+=>H+Ar+");
    model.component("comp1").physics("cpt").feature("col1").selection().set(1);
    model.component("comp1").physics("cpt").feature("col1").set("Nd", "fmf.n_G");
    model.component("comp1").physics("cpt").feature("col1").set("T", "T0");
    model.component("comp1").physics("cpt").feature("col1").set("ParticlesToAffect", "SingleSpecies");
    model.component("comp1").physics("cpt").feature("col1").create("ncex1", "NonResonantChargeExchange", 3);
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("xsec", "Qex1(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("dE", "E1");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("CountCollisions", true);
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1")
         .set("SpeciesToRelease", "IonAndNeutral");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("ReleasedIonProperties", "pp3");
    model.component("comp1").physics("cpt").feature("col1").feature("ncex1").set("ReleasedNeutralProperties", "pp2");
    model.component("comp1").physics("cpt").create("col2", "Collisions", 3);
    model.component("comp1").physics("cpt").feature("col2").label("H+Ar=>Ar+H+");
    model.component("comp1").physics("cpt").feature("col2").selection().set(1);
    model.component("comp1").physics("cpt").feature("col2").set("Nd", "fmf.n_G");
    model.component("comp1").physics("cpt").feature("col2").set("T", "T0");
    model.component("comp1").physics("cpt").feature("col2").set("ParticlesToAffect", "SingleSpecies");
    model.component("comp1").physics("cpt").feature("col2").set("AffectedParticleProperties", "pp2");
    model.component("comp1").physics("cpt").feature("col2").create("ncex1", "NonResonantChargeExchange", 3);
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("xsec", "Qex2(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("dE", "E2");
    model.component("comp1").physics("cpt").feature("col2").feature("ncex1").set("CountCollisions", true);
    model.component("comp1").physics("cpt").create("col3", "Collisions", 3);
    model.component("comp1").physics("cpt").feature("col3").label("H+Ar=>H+Ar+");
    model.component("comp1").physics("cpt").feature("col3").selection().set(1);
    model.component("comp1").physics("cpt").feature("col3").set("Nd", "fmf.n_G");
    model.component("comp1").physics("cpt").feature("col3").set("T", "T0");
    model.component("comp1").physics("cpt").feature("col3").set("ParticlesToAffect", "SingleSpecies");
    model.component("comp1").physics("cpt").feature("col3").set("AffectedParticleProperties", "pp2");
    model.component("comp1").physics("cpt").feature("col3").create("ncex1", "NonResonantChargeExchange", 3);
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("xsec", "Qex3(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("dE", "E3");
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("ReleasedIonProperties", "pp3");
    model.component("comp1").physics("cpt").feature("col3").feature("ncex1").set("CountCollisions", true);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(33, 34, 43, 44, 46, 47, 48, 49, 71, 72, 74, 75, 98, 99, 101, 103, 114, 117, 126, 128, 140, 142, 145, 146);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cpt", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Incident Molecular Flux (fmf)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Total Number Density (fmf)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "fmf.ntot");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Total Pressure (fmf)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "fmf.ptot");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Electric Potential (es)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "V");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Dipole");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Electric Field (es)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "es.normE");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Prism");
    model.result("pg5").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").label("Study 1/Solution 1 box selection");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("box1");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", -100, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 100, 1, 1);
    model.result("pg3").run();
    model.result("pg3").label("Pressure");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("unit", "Torr");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("Electric Potential");
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg4").feature("mslc1").set("ynumber", "0");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "number");
    model.result("pg4").feature("mslc1").set("znumber", "0");
    model.result("pg4").run();
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "number");
    model.result("pg4").feature("strmsl1").set("ynumber", "0");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "number");
    model.result("pg4").feature("strmsl1").set("znumber", "0");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Gas number density");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "fmf.ntot");
    model.result("pg6").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/fmf", false);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("time").setSolveFor("/physics/es", false);
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.01,1)*tmax");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol2").feature("t1").set("maxstepgenalpha", "tmax/nstep");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "part1");
    model.result("pg7").setIndex("looplevel", 101, 0);
    model.result("pg7").label("Particle Trajectories (cpt)");
    model.result("pg7").create("traj1", "ParticleTrajectories");
    model.result("pg7").feature("traj1").set("pointtype", "point");
    model.result("pg7").feature("traj1").set("linetype", "none");
    model.result("pg7").feature("traj1").create("col1", "Color");
    model.result("pg7").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "part1");
    model.result("pg8").setIndex("looplevel", 101, 0);
    model.result("pg8").label("Average Beam Position (cpt)");
    model.result("pg8").create("pttraj1", "PointTrajectories");
    model.result("pg8").feature("pttraj1").set("plotdata", "global");
    model.result("pg8").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg8").feature("pttraj1").create("col1", "Color");
    model.result("pg8").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg7").run();
    model.result().dataset().duplicate("part2", "part1");
    model.result().dataset("part2").selection().geom("geom1", 2);
    model.result().dataset("part2").selection().set(8);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("traj1").set("linetype", "line");
    model.result("pg7").feature("traj1").set("pointtype", "none");
    model.result("pg7").run();
    model.result("pg7").feature("traj1").feature("col1").set("expr", "cpt.Z");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result().remove("pg8");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Efficiency");
    model.result().numerical("gev1").set("data", "part2");
    model.result().numerical("gev1").setIndex("expr", "cpt.Nsel/N0*100", 0);
    model.result().numerical("gev1").setIndex("descr", "cpt.Nsel/N0*100", 0);
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("Nc1/Nctot");
    model.result().numerical("gev2").setIndex("expr", "Nc1/Nctot*100", 0);
    model.result().numerical("gev2").setIndex("descr", "Nc1/Nctot*100", 0);
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").label("Nc2/Nctot");
    model.result().numerical("gev3").setIndex("expr", "Nc2/Nctot*100", 0);
    model.result().numerical("gev3").setIndex("descr", "Nc2/Nctot*100", 0);
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").label("Nc3/Nctot");
    model.result().numerical("gev4").setIndex("expr", "Nc3/Nctot*100", 0);
    model.result().numerical("gev4").setIndex("descr", "Nc3/Nctot*100", 0);
    model.result().numerical().duplicate("gev5", "gev4");
    model.result().numerical("gev5").label("All Secondary used?");
    model.result().numerical("gev5").setIndex("expr", "cpt.cptop_all1(cpt.fs<1)<1", 0);
    model.result().numerical("gev5").setIndex("descr", "cpt.cptop_all1(cpt.fs<1)<1", 0);

    model.study().create("std3");
    model.study("std3").label("Plot Geometry");
    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().named("box1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("Geometry");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "1");
    model.result("pg8").feature("surf1").set("coloring", "uniform");
    model.result("pg8").feature("surf1").set("color", "gray");
    model.result("pg8").run();

    model.title(null);

    model.description("");

    model.label("charge_exchange_cell_simulator_embedded.mph");

    model.result("pg8").run();

    model.title("\u7535\u8377\u4ea4\u6362\u5ba4\u6a21\u62df\u5668");

    model
         .description("\u7535\u8377\u4ea4\u6362\u5ba4\u7531\u771f\u7a7a\u5ba4\u5185\u9ad8\u538b\u4e0b\u7684\u6c14\u4f53\u533a\u57df\u7ec4\u6210\u3002\u5f53\u79bb\u5b50\u675f\u4e0e\u9ad8\u5bc6\u5ea6\u6c14\u4f53\u76f8\u4e92\u4f5c\u7528\u65f6\uff0c\u79bb\u5b50\u4e0e\u6c14\u4f53\u4f1a\u53d1\u751f\u7535\u8377\u4ea4\u6362\u53cd\u5e94\uff0c\u7136\u540e\u4ea7\u751f\u9ad8\u80fd\u4e2d\u6027\u7c92\u5b50\u3002\u5176\u4e2d\u53ef\u80fd\u53ea\u6709\u4e00\u5c0f\u90e8\u5206\u675f\u79bb\u5b50\u4f1a\u53d1\u751f\u7535\u8377\u4ea4\u6362\u53cd\u5e94\u3002\u56e0\u6b64\uff0c\u4e3a\u4e86\u4e2d\u548c\u79bb\u5b50\u675f\uff0c\u672c\u4f8b\u5728\u7535\u6c60\u5916\u90e8\u653e\u7f6e\u4e86\u4e00\u5bf9\u5e26\u7535\u504f\u8f6c\u677f\uff0c\u901a\u8fc7\u8fd9\u79cd\u65b9\u5f0f\uff0c\u53ef\u4ee5\u4ea7\u751f\u9ad8\u80fd\u4e2d\u6027\u6e90\u3002\n\n\u6b64 App \u6a21\u62df\u8d28\u5b50\u675f\u4e0e\u5305\u542b\u4e2d\u6027\u6c29\u7684\u7535\u8377\u4ea4\u6362\u5ba4\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u3002\u7528\u6237\u8f93\u5165\u5305\u62ec\uff1a\u6c14\u5ba4\u548c\u771f\u7a7a\u5ba4\u7684\u591a\u4e2a\u51e0\u4f55\u53c2\u6570\u3001\u675f\u6d41\u5c5e\u6027\u4ee5\u53ca\u7528\u4e8e\u4f7f\u5176\u4f59\u79bb\u5b50\u504f\u8f6c\u7684\u5e26\u7535\u677f\u7684\u5c5e\u6027\u3002\n\n\u8be5\u4eff\u771f App \u8ba1\u7b97\u7535\u8377\u4ea4\u6362\u5ba4\u7684\u6548\u7387\uff08\u4ee5\u4e2d\u548c\u79bb\u5b50\u7684\u6bd4\u4f8b\u6765\u6d4b\u91cf\uff09\uff0c\u5e76\u8bb0\u5f55\u6574\u4e2a\u8fc7\u7a0b\u4e2d\u53d1\u751f\u7684\u4e0d\u540c\u78b0\u649e\u7c7b\u578b\u7684\u7edf\u8ba1\u4fe1\u606f\u3002");

    model.setExpectedComputationTime("1 \u5206 30 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u901a\u8fc7\u7535\u8377\u4ea4\u6362\u5ba4\uff0c\u53ef\u4ee5\u83b7\u53d6\u9ad8\u901f\u4e2d\u6027\u7c92\u5b50\u675f\u3002\u8fd9\u662f\u4e00\u4e2a\u653e\u7f6e\u5728\u79bb\u5b50\u675f\u8def\u5f84\u4e0a\u7684\u9ad8\u5bc6\u5ea6\u6c14\u4f53\u533a\u57df\uff0c\u53ef\u4ee5\u4ea7\u751f\u4e00\u79cd\u4ecb\u8d28\uff0c\u5feb\u901f\u79bb\u5b50\u4f1a\u5728\u5176\u4e2d\u88ab\u4e2d\u548c\uff0c\u4ece\u800c\u5728\u4ea4\u6362\u5ba4\u51fa\u53e3\u4ea7\u751f\u4e00\u675f\u4e2d\u6027\u7c92\u5b50\u3002\u7531\u4e8e\u7535\u5b50\u6355\u83b7\u6982\u7387\u4e0d\u662f\u5f88\u9ad8\uff0c\u5e26\u7535\u7c92\u5b50\u5728\u79bb\u5f00\u4ea4\u6362\u5ba4\u65f6\u4ecd\u7136\u5b58\u5728\u4e8e\u7c92\u5b50\u675f\u4e2d\u3002\u4e3a\u4e86\u5728\u4ea4\u6362\u8fc7\u7a0b\u7ed3\u675f\u65f6\u5f97\u5230\u7eaf\u4e2d\u6027\u7c92\u5b50\u675f\uff0c\u53ef\u4ee5\u4f7f\u7528\u5e26\u7535\u677f\u5728\u7c92\u5b50\u675f\u5230\u8fbe\u76ee\u6807\u4e4b\u524d\u4f7f\u5e26\u7535\u7c92\u5b50\u53d1\u751f\u504f\u8f6c\u3002\n\u672c\u6a21\u578b\u4f7f\u7528\u201c\u5206\u5b50\u6d41\u6a21\u5757\u201d\u8ba1\u7b97\u7535\u8377\u4ea4\u6362\u5ba4\u4e2d\u7684\u538b\u529b\uff0c\u901a\u8fc7\u201c\u9759\u7535\u201d\u63a5\u53e3\u8ba1\u7b97\u4f7f\u5e26\u7535\u7c92\u5b50\u53d1\u751f\u504f\u8f6c\u7684\u7535\u573a\uff0c\u5e76\u4f7f\u7528\u201c\u5e26\u7535\u7c92\u5b50\u8ffd\u8e2a\u201d\u63a5\u53e3\u8ba1\u7b97\u8f68\u8ff9\uff0c\u4ee5\u53ca\u6a21\u62df\u7c92\u5b50\u4e0e\u5468\u56f4\u4e2d\u6027\u539f\u5b50\u4e4b\u95f4\u7684\u78b0\u649e\u3002\u5728\u8fd9\u4e2a\u7279\u5b9a App \u4e2d\uff0c\u4e00\u675f\u9ad8\u80fd\u8d28\u5b50\u4e0e\u6c29\u539f\u5b50\u53d1\u751f\u7535\u8377\u4ea4\u6362\u3002");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").label("\u7a33\u6001\u7814\u7a76");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").label("\u77ac\u6001\u7814\u7a76");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("std1").set("noderef", "std2");
    model.result().report("rpt1").set("filename", "user:///charge_exchange_cell_simulator");
    model.result().report("rpt1").set("imagesize", "xlarge");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u53d8\u91cf");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("var1", "Variables");
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("pg1").set("noderef", "pg7");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u6d3e\u751f\u503c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").label("\u6548\u7387");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature()
         .create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").label("\u78b0\u649e\u7edf\u8ba1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("num1")
         .set("noderef", "gev2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .duplicate("num2", "num1");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("num2")
         .set("noderef", "gev3");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .duplicate("num3", "num2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("num3")
         .set("noderef", "gev4");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec1").label("\u538b\u529b");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec2").label("\u7535\u52bf");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec2").feature("pg1")
         .set("noderef", "pg4");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec3")
         .label("\u6c14\u4f53\u6570\u5bc6\u5ea6");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec3").feature("pg1")
         .set("noderef", "pg5");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec4").label("\u7c92\u5b50\u8f68\u8ff9");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec4").feature("pg1")
         .set("noderef", "pg6");

    model.title("\u7535\u8377\u4ea4\u6362\u5ba4\u6a21\u62df\u5668");

    model
         .description("\u7535\u8377\u4ea4\u6362\u5ba4\u7531\u771f\u7a7a\u5ba4\u5185\u9ad8\u538b\u4e0b\u7684\u6c14\u4f53\u533a\u57df\u7ec4\u6210\u3002\u5f53\u79bb\u5b50\u675f\u4e0e\u9ad8\u5bc6\u5ea6\u6c14\u4f53\u76f8\u4e92\u4f5c\u7528\u65f6\uff0c\u79bb\u5b50\u4e0e\u6c14\u4f53\u4f1a\u53d1\u751f\u7535\u8377\u4ea4\u6362\u53cd\u5e94\uff0c\u7136\u540e\u4ea7\u751f\u9ad8\u80fd\u4e2d\u6027\u7c92\u5b50\u3002\u5176\u4e2d\u53ef\u80fd\u53ea\u6709\u4e00\u5c0f\u90e8\u5206\u675f\u79bb\u5b50\u4f1a\u53d1\u751f\u7535\u8377\u4ea4\u6362\u53cd\u5e94\u3002\u56e0\u6b64\uff0c\u4e3a\u4e86\u4e2d\u548c\u79bb\u5b50\u675f\uff0c\u672c\u4f8b\u5728\u7535\u6c60\u5916\u90e8\u653e\u7f6e\u4e86\u4e00\u5bf9\u5e26\u7535\u504f\u8f6c\u677f\uff0c\u901a\u8fc7\u8fd9\u79cd\u65b9\u5f0f\uff0c\u53ef\u4ee5\u4ea7\u751f\u9ad8\u80fd\u4e2d\u6027\u6e90\u3002\n\n\u6b64 App \u6a21\u62df\u8d28\u5b50\u675f\u4e0e\u5305\u542b\u4e2d\u6027\u6c29\u7684\u7535\u8377\u4ea4\u6362\u5ba4\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u3002\u7528\u6237\u8f93\u5165\u5305\u62ec\uff1a\u6c14\u5ba4\u548c\u771f\u7a7a\u5ba4\u7684\u591a\u4e2a\u51e0\u4f55\u53c2\u6570\u3001\u675f\u6d41\u5c5e\u6027\u4ee5\u53ca\u7528\u4e8e\u4f7f\u5176\u4f59\u79bb\u5b50\u504f\u8f6c\u7684\u5e26\u7535\u677f\u7684\u5c5e\u6027\u3002\n\n\u8be5\u4eff\u771f App \u8ba1\u7b97\u7535\u8377\u4ea4\u6362\u5ba4\u7684\u6548\u7387\uff08\u4ee5\u4e2d\u548c\u79bb\u5b50\u7684\u6bd4\u4f8b\u6765\u6d4b\u91cf\uff09\uff0c\u5e76\u8bb0\u5f55\u6574\u4e2a\u8fc7\u7a0b\u4e2d\u53d1\u751f\u7684\u4e0d\u540c\u78b0\u649e\u7c7b\u578b\u7684\u7edf\u8ba1\u4fe1\u606f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("charge_exchange_cell_simulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
