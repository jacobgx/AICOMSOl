/*
 * ccp_argon_oxygen.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:00 by COMSOL 6.3.0.290. */
public class ccp_argon_oxygen {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Capacitively_Coupled_Plasmas");

    model.param().set("xO2", "0.9");
    model.param().set("xAr", "1-xO2");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("coord", new double[]{0, 4.5});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics().create("ptp", "ColdPlasmaTimePeriodic", "geom1");
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").create("xsec2", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").create("xsec3", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").create("eir1", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir2", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir3", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir4", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir5", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir6", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir7", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir8", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir9", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir10", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir11", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir12", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir13", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir14", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir15", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir16", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir17", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir18", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir19", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir20", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir21", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir22", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir23", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir24", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir25", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("eir26", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").create("rxn1", "Reaction", 1);
    model.component("comp1").physics("ptp").create("rxn2", "Reaction", 1);
    model.component("comp1").physics("ptp").create("rxn3", "Reaction", 1);
    model.component("comp1").physics("ptp").create("rxn4", "Reaction", 1);
    model.component("comp1").physics("ptp").create("rxn5", "Reaction", 1);
    model.component("comp1").physics("ptp").create("rxn6", "Reaction", 1);
    model.component("comp1").physics("ptp").create("e", "Species", 1);
    model.component("comp1").physics("ptp").create("O2", "Species", 1);
    model.component("comp1").physics("ptp").create("O", "Species", 1);
    model.component("comp1").physics("ptp").create("O_1m", "Species", 1);
    model.component("comp1").physics("ptp").create("O2a1Dg", "Species", 1);
    model.component("comp1").physics("ptp").create("O1D", "Species", 1);
    model.component("comp1").physics("ptp").create("O2_1p", "Species", 1);
    model.component("comp1").physics("ptp").create("O_1p", "Species", 1);
    model.component("comp1").physics("ptp").create("Ar", "Species", 1);
    model.component("comp1").physics("ptp").create("Ars", "Species", 1);
    model.component("comp1").physics("ptp").create("Ar_1p", "Species", 1);
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr1").selection().all();
    model.component("comp1").physics("ptp").create("sr2", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr2").selection().all();
    model.component("comp1").physics("ptp").create("sr3", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr3").selection().all();
    model.component("comp1").physics("ptp").create("sr4", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr4").selection().all();
    model.component("comp1").physics("ptp").create("sr5", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr5").selection().all();
    model.component("comp1").physics("ptp").create("sr6", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr6").selection().all();
    model.component("comp1").physics("ptp").create("sr7", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr7").selection().all();
    model.component("comp1").physics("ptp").create("sr8", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr8").selection().all();
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 0);
    model.component("comp1").physics("ptp").feature("mct1").selection().set(1);
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("ptp").feature("wall1").selection().all();
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 0);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(2);
    model.component("comp1").physics("ptp").create("in1", "Inflow", 0);
    model.component("comp1").physics("ptp").feature("in1").selection().set(1);
    model.component("comp1").physics("ptp").create("out1", "Outflow", 0);
    model.component("comp1").physics("ptp").feature("out1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");

    model.component("comp1").view("view1").axis().set("xmin", -0.22499990463256836);
    model.component("comp1").view("view1").axis().set("xmax", 4.724999904632568);

    model.component("comp1").physics("ptp").prop("CrossSectionArea").set("A", "pi*(14.36[cm])^2");
    model.component("comp1").physics("ptp").prop("Stabilization").set("SourceStabilization", true);
    model.component("comp1").physics("ptp").prop("Stabilization").set("ReactionSourceStabilization", true);
    model.component("comp1").physics("ptp").prop("InconsistentStabilization").set("IsotropicDiffusionIons", true);
    model.component("comp1").physics("ptp").prop("TransportSettings").set("MixtureDiffusionCorrection", true);
    model.component("comp1").physics("ptp").prop("EEDFSettings").set("eedf", "Maxwellian");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings")
         .set("HeavySpeciesSelection", "BaseGeometry");
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "O2_phelps_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec2").set("Filepath", "O_morgan_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec3").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("ptp").feature("eir1")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O"}, {"O_1m"}});
    model.component("comp1").physics("ptp").feature("eir1").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir1").set("revSpecies", new String[][]{{"O"}, {"O-"}});
    model.component("comp1").physics("ptp").feature("eir1").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir1")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir1")
         .set("revArray", new int[][]{{0}, {0}, {1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir1")
         .set("stoichArray", new int[][]{{-1}, {-1}, {1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir1").set("formula", "e+O2=>O+O-");
    model.component("comp1").physics("ptp").feature("eir1").set("type", "Attachment");
    model.component("comp1").physics("ptp").feature("eir1").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir1")
         .set("xdata", new String[][]{{"4.4"}, 
         {"4.9"}, 
         {"5.38"}, 
         {"5.86"}, 
         {"6.1"}, 
         {"6.48"}, 
         {"6.77"}, 
         {"7.05"}, 
         {"7.3"}, 
         {"7.53"}, 
         {"7.77"}, 
         {"8.0"}, 
         {"8.25"}, 
         {"8.73"}, 
         {"9.2"}, 
         {"9.68"}, 
         {"10.15"}, 
         {"11.35"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir1")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"2.3E-23"}, 
         {"7.2E-23"}, 
         {"1.08E-22"}, 
         {"1.38E-22"}, 
         {"1.52E-22"}, 
         {"1.56E-22"}, 
         {"1.48E-22"}, 
         {"1.31E-22"}, 
         {"1.1E-22"}, 
         {"8.4E-23"}, 
         {"5.4E-23"}, 
         {"2.8E-23"}, 
         {"1.4E-23"}, 
         {"8.0E-24"}, 
         {"8.0E-24"}, 
         {"8.0E-24"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir1").active(true);
    model.component("comp1").physics("ptp").feature("eir1").label("1: e+O2=>O+O-");
    model.component("comp1").physics("ptp").feature("eir2").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir2").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir2").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir2").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir2")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir2")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir2")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir2").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir2").set("mratio", "1.71e-5");
    model.component("comp1").physics("ptp").feature("eir2").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir2")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.001"}, 
         {"0.002"}, 
         {"0.003"}, 
         {"0.005"}, 
         {"0.007"}, 
         {"0.0085"}, 
         {"0.01"}, 
         {"0.015"}, 
         {"0.02"}, 
         {"0.03"}, 
         {"0.04"}, 
         {"0.05"}, 
         {"0.07"}, 
         {"0.1"}, 
         {"0.12"}, 
         {"0.15"}, 
         {"0.17"}, 
         {"0.2"}, 
         {"0.25"}, 
         {"0.3"}, 
         {"0.35"}, 
         {"0.4"}, 
         {"0.5"}, 
         {"0.7"}, 
         {"1.0"}, 
         {"1.2"}, 
         {"1.3"}, 
         {"1.5"}, 
         {"1.7"}, 
         {"1.9"}, 
         {"2.1"}, 
         {"2.2"}, 
         {"2.5"}, 
         {"2.8"}, 
         {"3.0"}, 
         {"3.3"}, 
         {"3.6"}, 
         {"4.0"}, 
         {"4.5"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}, 
         {"17.0"}, 
         {"20.0"}, 
         {"25.0"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"75.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir2")
         .set("ydata", new double[][]{{3.5E-21}, {3.5E-21}, {3.6E-21}, {4.0E-21}, {5.0E-21}, {5.8E-21}, {6.4E-21}, {7.0E-21}, {8.7E-21}, {9.9E-21}, {1.24E-20}, {1.44E-20}, {1.6E-20}, {2.1E-20}, {2.5E-20}, {2.8E-20}, {3.1E-20}, {3.3E-20}, {3.6E-20}, {4.1E-20}, {4.5E-20}, {4.7E-20}, {5.2E-20}, {5.7E-20}, {6.1E-20}, {7.2E-20}, {7.9E-20}, {7.9E-20}, {7.6E-20}, {7.3E-20}, {6.9E-20}, {6.6E-20}, {6.5E-20}, {6.1E-20}, {5.8E-20}, {5.7E-20}, {5.5E-20}, {5.45E-20}, {5.5E-20}, {5.55E-20}, {5.6E-20}, {6.0E-20}, {6.6E-20}, {7.1E-20}, {8.0E-20}, {8.5E-20}, {8.8E-20}, {8.7E-20}, {8.6E-20}, {8.2E-20}, {8.0E-20}, {7.7E-20}, {6.8E-20}, {6.5E-20}, {6.7E-20}, {6.0E-20}, {4.9E-20}, {3.6E-20}, {2.9E-20}, {2.12E-20}, {1.48E-20}, {1.14E-20}, {7.9E-21}, {5.1E-21}, {3.8E-21}, {2.8E-21}});
    model.component("comp1").physics("ptp").feature("eir2").active(true);
    model.component("comp1").physics("ptp").feature("eir2").label("2: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir3").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir3").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir3").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir3").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir3")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir3")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir3")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir3").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir3").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir3").set("de", "2.000000e-2");
    model.component("comp1").physics("ptp").feature("eir3").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir3")
         .set("xdata", new double[][]{{0.07}, {0.08}, {0.1}, {0.2}, {0.21}, {0.22}, {0.32}, {0.33}, {0.35}, {0.44}, {0.45}, {0.47}, {0.56}, {0.57}, {0.59}, {0.68}, {0.69}, {0.71}, {0.79}, {0.8}, {0.81}, {0.9}, {0.91}, {0.93}, {1.02}, {1.03}, {1.05}, {1.13}, {1.14}, {1.16}, {1.22}, {1.23}, {1.26}, {1.34}, {1.35}, {1.37}, {1.44}, {1.45}, {1.47}, {1.54}, {1.55}, {1.57}, {1.64}, {1.65}, {1.67}});
    model.component("comp1").physics("ptp").feature("eir3")
         .set("ydata", new String[][]{{"0.0"}, 
         {"5.4E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.16E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.84E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.4E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.72E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.04E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.36E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.4E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"7.2E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.68E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.4E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.2E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.8E-23"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir3").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir3").active(true);
    model.component("comp1").physics("ptp").feature("eir3").label("3: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir4").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir4").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir4").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir4").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir4")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir4")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir4")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir4").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir4").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir4").set("de", "1.900000e-1");
    model.component("comp1").physics("ptp").feature("eir4").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir4")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.19"}, 
         {"0.2"}, 
         {"0.21"}, 
         {"0.23"}, 
         {"0.32"}, 
         {"0.33"}, 
         {"0.35"}, 
         {"0.44"}, 
         {"0.45"}, 
         {"0.47"}, 
         {"0.56"}, 
         {"0.57"}, 
         {"0.59"}, 
         {"0.68"}, 
         {"0.69"}, 
         {"0.71"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"3.5"}});
    model.component("comp1").physics("ptp").feature("eir4")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"1.0E-23"}, 
         {"1.0E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.35E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.85E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.0E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.0E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.85E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.125E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.75E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.9E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.0E-24"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir4").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir4").active(true);
    model.component("comp1").physics("ptp").feature("eir4").label("4: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir5").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir5").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir5").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir5").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir5")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir5")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir5")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir5").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir5").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir5").set("de", "1.900000e-1");
    model.component("comp1").physics("ptp").feature("eir5").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir5")
         .set("xdata", new String[][]{{"4.0"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"20.0"}, 
         {"45.0"}});
    model.component("comp1").physics("ptp").feature("eir5")
         .set("ydata", new String[][]{{"0.0"}, 
         {"4.2E-22"}, 
         {"1.0E-21"}, 
         {"1.76E-21"}, 
         {"2.31E-21"}, 
         {"2.47E-21"}, 
         {"2.34E-21"}, 
         {"1.86E-21"}, 
         {"1.43E-21"}, 
         {"1.02E-21"}, 
         {"7.1E-22"}, 
         {"4.0E-22"}, 
         {"1.0E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir5").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir5").active(true);
    model.component("comp1").physics("ptp").feature("eir5").label("5: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir6").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir6").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir6").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir6").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir6")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir6")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir6")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir6").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir6").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir6").set("de", "3.800000e-1");
    model.component("comp1").physics("ptp").feature("eir6").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir6")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.38"}, 
         {"0.44"}, 
         {"0.45"}, 
         {"0.47"}, 
         {"0.56"}, 
         {"0.57"}, 
         {"0.59"}, 
         {"0.68"}, 
         {"0.69"}, 
         {"0.71"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir6")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.4E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.35E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.65E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.0E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.5E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.85E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.4E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir6").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir6").active(true);
    model.component("comp1").physics("ptp").feature("eir6").label("6: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir7").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir7").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir7").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir7").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir7")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir7")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir7")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir7").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir7").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir7").set("de", "3.800000e-1");
    model.component("comp1").physics("ptp").feature("eir7").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir7")
         .set("xdata", new String[][]{{"4.0"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"20.0"}, 
         {"45.0"}});
    model.component("comp1").physics("ptp").feature("eir7")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.8E-22"}, 
         {"4.0E-22"}, 
         {"7.3E-22"}, 
         {"9.4E-22"}, 
         {"1.1E-21"}, 
         {"1.09E-21"}, 
         {"9.3E-22"}, 
         {"7.3E-22"}, 
         {"5.1E-22"}, 
         {"2.8E-22"}, 
         {"1.3E-22"}, 
         {"5.0E-23"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir7").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir7").active(true);
    model.component("comp1").physics("ptp").feature("eir7").label("7: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir8").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir8").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir8").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir8").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir8")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir8")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir8")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir8").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir8").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir8").set("de", "5.700000e-1");
    model.component("comp1").physics("ptp").feature("eir8").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir8")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.57"}, 
         {"0.68"}, 
         {"0.69"}, 
         {"0.71"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"20.0"}, 
         {"45.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir8")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.7E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.15E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.2E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.5E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.5E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.0E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.25E-22"}, 
         {"3.63E-22"}, 
         {"5.88E-22"}, 
         {"7.5E-22"}, 
         {"6.75E-22"}, 
         {"5.63E-22"}, 
         {"4.75E-22"}, 
         {"3.0E-22"}, 
         {"1.75E-22"}, 
         {"8.8E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir8").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir8").active(true);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("ptp").feature("eir8").label("8: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir9").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir9").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir9").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir9").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir9")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir9")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir9")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir9").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir9").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir9").set("de", "7.500000e-1");
    model.component("comp1").physics("ptp").feature("eir9").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir9")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.75"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir9")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.0E-24"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.15E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.35E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.85E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.15E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.75E-22"}, 
         {"3.5E-22"}, 
         {"4.13E-22"}, 
         {"4.62E-22"}, 
         {"3.13E-22"}, 
         {"2.5E-22"}, 
         {"1.75E-22"}, 
         {"8.8E-23"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir9").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir9").active(true);
    model.component("comp1").physics("ptp").feature("eir9").label("9: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir10")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O2a1Dg"}});
    model.component("comp1").physics("ptp").feature("eir10").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir10").set("revSpecies", new String[][]{{"e"}, {"O2a1Dg"}});
    model.component("comp1").physics("ptp").feature("eir10").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir10")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir10")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir10")
         .set("stoichArray", new int[][]{{0}, {-1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir10").set("formula", "e+O2=>e+O2a1Dg");
    model.component("comp1").physics("ptp").feature("eir10").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir10").set("de", "9.770000e-1");
    model.component("comp1").physics("ptp").feature("eir10").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir10")
         .set("xdata", new String[][]{{"0.977"}, 
         {"1.5"}, 
         {"2.0"}, 
         {"3.0"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"5.62"}, 
         {"5.91"}, 
         {"6.19"}, 
         {"6.53"}, 
         {"6.99"}, 
         {"7.61"}, 
         {"7.89"}, 
         {"8.96"}, 
         {"10.04"}, 
         {"13.0"}, 
         {"15.1"}, 
         {"17.5"}, 
         {"20.5"}, 
         {"24.9"}, 
         {"30.9"}, 
         {"41.0"}, 
         {"45.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir10")
         .set("ydata", new String[][]{{"0.0"}, 
         {"5.8E-23"}, 
         {"1.53E-22"}, 
         {"3.8E-22"}, 
         {"4.9E-22"}, 
         {"5.7E-22"}, 
         {"7.4E-22"}, 
         {"8.25E-22"}, 
         {"8.62E-22"}, 
         {"8.88E-22"}, 
         {"9.08E-22"}, 
         {"9.14E-22"}, 
         {"8.91E-22"}, 
         {"8.63E-22"}, 
         {"7.68E-22"}, 
         {"6.79E-22"}, 
         {"5.27E-22"}, 
         {"4.55E-22"}, 
         {"3.87E-22"}, 
         {"3.24E-22"}, 
         {"2.56E-22"}, 
         {"1.96E-22"}, 
         {"1.37E-22"}, 
         {"1.2E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir10").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir10").active(true);
    model.component("comp1").physics("ptp").feature("eir10").label("10: e+O2=>e+O2a1Dg");
    model.component("comp1").physics("ptp").feature("eir11").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir11").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir11").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir11").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir11")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir11")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir11")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir11").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir11").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir11").set("de", "1.627000e+0");
    model.component("comp1").physics("ptp").feature("eir11").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir11")
         .set("xdata", new String[][]{{"1.627"}, 
         {"2.0"}, 
         {"3.0"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"5.69"}, 
         {"6.54"}, 
         {"7.34"}, 
         {"8.41"}, 
         {"9.26"}, 
         {"10.0"}, 
         {"13.0"}, 
         {"14.9"}, 
         {"17.0"}, 
         {"19.4"}, 
         {"20.7"}, 
         {"22.5"}, 
         {"24.0"}, 
         {"28.0"}, 
         {"35.1"}, 
         {"41.9"}, 
         {"45.1"}, 
         {"1000.0"}});
    model.component("comp1").physics("ptp").feature("eir11")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.6E-23"}, 
         {"9.7E-23"}, 
         {"1.33E-22"}, 
         {"1.49E-22"}, 
         {"1.82E-22"}, 
         {"1.94E-22"}, 
         {"1.94E-22"}, 
         {"1.91E-22"}, 
         {"1.83E-22"}, 
         {"1.74E-22"}, 
         {"1.6E-22"}, 
         {"1.3E-22"}, 
         {"1.3E-22"}, 
         {"1.3E-22"}, 
         {"1.25E-22"}, 
         {"1.25E-22"}, 
         {"1.1E-22"}, 
         {"1.0E-22"}, 
         {"8.0E-23"}, 
         {"6.3E-23"}, 
         {"1.8E-23"}, 
         {"5.0E-24"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir11").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir11").active(true);
    model.component("comp1").physics("ptp").feature("eir11").label("11: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir12").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir12").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir12").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir12").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir12")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir12")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir12")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir12").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir12").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir12").set("de", "4.500000e+0");
    model.component("comp1").physics("ptp").feature("eir12").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir12")
         .set("xdata", new String[][]{{"4.5"}, 
         {"4.8"}, 
         {"5.0"}, 
         {"5.5"}, 
         {"6.0"}, 
         {"6.5"}, 
         {"7.0"}, 
         {"7.5"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}});
    model.component("comp1").physics("ptp").feature("eir12")
         .set("ydata", new String[][]{{"0.0"}, 
         {"3.0E-23"}, 
         {"9.0E-23"}, 
         {"3.0E-22"}, 
         {"6.5E-22"}, 
         {"8.5E-22"}, 
         {"9.5E-22"}, 
         {"1.0E-21"}, 
         {"1.0E-21"}, 
         {"8.5E-22"}, 
         {"7.0E-22"}, 
         {"4.5E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir12").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir12").active(true);
    model.component("comp1").physics("ptp").feature("eir12").label("12: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir13").set("Species", new String[][]{{"e"}, {"O2"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir13").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir13").set("revSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir13").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir13")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir13")
         .set("revArray", new int[][]{{1}, {0}, {2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir13")
         .set("stoichArray", new int[][]{{0}, {-1}, {2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir13").set("formula", "e+O2=>e+O+O");
    model.component("comp1").physics("ptp").feature("eir13").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir13").set("de", "6.000000e+0");
    model.component("comp1").physics("ptp").feature("eir13").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir13")
         .set("xdata", new String[][]{{"6.0"}, 
         {"7.0"}, 
         {"7.8"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}, 
         {"17.0"}, 
         {"20.0"}, 
         {"45.0"}, 
         {"100.0"}});
    model.component("comp1").physics("ptp").feature("eir13")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.5E-21"}, 
         {"2.3E-21"}, 
         {"2.3E-21"}, 
         {"2.1E-21"}, 
         {"1.65E-21"}, 
         {"1.05E-21"}, 
         {"6.5E-22"}, 
         {"4.75E-22"}, 
         {"1.9E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir13").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir13").active(true);
    model.component("comp1").physics("ptp").feature("eir13").label("13: e+O2=>e+O+O");
    model.component("comp1").physics("ptp").feature("eir14")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O"}, {"O1D"}});
    model.component("comp1").physics("ptp").feature("eir14").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir14")
         .set("revSpecies", new String[][]{{"e"}, {"O"}, {"O1D"}});
    model.component("comp1").physics("ptp").feature("eir14").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir14")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir14")
         .set("revArray", new int[][]{{1}, {0}, {1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir14")
         .set("stoichArray", new int[][]{{0}, {-1}, {1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir14").set("formula", "e+O2=>e+O+O1D");
    model.component("comp1").physics("ptp").feature("eir14").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir14").set("de", "8.400000e+0");
    model.component("comp1").physics("ptp").feature("eir14").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir14")
         .set("xdata", new String[][]{{"8.4"}, 
         {"9.4"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir14")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.0E-20"}, 
         {"9.0E-21"}, 
         {"7.0E-21"}, 
         {"5.4E-21"}, 
         {"3.2E-21"}, 
         {"2.7E-21"}, 
         {"1.7E-21"}, 
         {"1.09E-21"}, 
         {"8.0E-22"}, 
         {"5.8E-22"}, 
         {"4.2E-22"}, 
         {"3.3E-22"}, 
         {"2.4E-22"}, 
         {"1.6E-22"}, 
         {"1.2E-22"}, 
         {"9.0E-23"}});
    model.component("comp1").physics("ptp").feature("eir14").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir14").active(true);
    model.component("comp1").physics("ptp").feature("eir14").label("14: e+O2=>e+O+O1D");
    model.component("comp1").physics("ptp").feature("eir15").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir15").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir15").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir15").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir15")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir15")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir15")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir15").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir15").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir15").set("de", "9.970000e+0");
    model.component("comp1").physics("ptp").feature("eir15").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir15")
         .set("xdata", new String[][]{{"10.0"}, 
         {"20.0"}, 
         {"30.0"}, 
         {"40.0"}, 
         {"50.0"}, 
         {"60.0"}, 
         {"70.0"}, 
         {"80.0"}, 
         {"100.0"}, 
         {"120.0"}, 
         {"150.0"}, 
         {"170.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}});
    model.component("comp1").physics("ptp").feature("eir15")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.3E-22"}, 
         {"2.6E-22"}, 
         {"4.0E-22"}, 
         {"5.0E-22"}, 
         {"6.0E-22"}, 
         {"6.5E-22"}, 
         {"7.0E-22"}, 
         {"7.0E-22"}, 
         {"5.0E-22"}, 
         {"4.0E-22"}, 
         {"3.5E-22"}, 
         {"3.0E-22"}, 
         {"2.0E-22"}, 
         {"1.2E-22"}, 
         {"8.0E-23"}, 
         {"5.0E-23"}, 
         {"0.0"}});
    model.component("comp1").physics("ptp").feature("eir15").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir15").active(true);
    model.component("comp1").physics("ptp").feature("eir15").label("15: e+O2=>e+O2");
    model.component("comp1").physics("ptp").feature("eir16")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O2_1p"}});
    model.component("comp1").physics("ptp").feature("eir16").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("eir16").set("revSpecies", new String[][]{{"e"}, {"O2+"}});
    model.component("comp1").physics("ptp").feature("eir16").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir16")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir16")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir16")
         .set("stoichArray", new int[][]{{1}, {-1}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir16").set("formula", "e+O2=>2e+O2+");
    model.component("comp1").physics("ptp").feature("eir16").set("type", "Ionization");
    model.component("comp1").physics("ptp").feature("eir16").set("de", "1.206000e+1");
    model.component("comp1").physics("ptp").feature("eir16").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir16")
         .set("xdata", new String[][]{{"12.06"}, 
         {"13.0"}, 
         {"18.0"}, 
         {"28.0"}, 
         {"38.0"}, 
         {"48.0"}, 
         {"58.0"}, 
         {"68.0"}, 
         {"78.0"}, 
         {"88.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("ptp").feature("eir16")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.3E-22"}, 
         {"2.0E-21"}, 
         {"7.4E-21"}, 
         {"1.32E-20"}, 
         {"1.8E-20"}, 
         {"2.1E-20"}, 
         {"2.33E-20"}, 
         {"2.5E-20"}, 
         {"2.6E-20"}, 
         {"2.7E-20"}, 
         {"2.7E-20"}, 
         {"2.5E-20"}, 
         {"2.17E-20"}, 
         {"1.66E-20"}, 
         {"1.35E-20"}, 
         {"1.04E-20"}, 
         {"7.6E-21"}, 
         {"6.0E-21"}, 
         {"4.2E-21"}, 
         {"2.7E-21"}, 
         {"2.0E-21"}, 
         {"1.4E-21"}});
    model.component("comp1").physics("ptp").feature("eir16").active(true);
    model.component("comp1").physics("ptp").feature("eir16").label("16: e+O2=>2e+O2+");
    model.component("comp1").physics("ptp").feature("eir17").set("Species", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir17").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir17").set("revSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir17").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir17")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir17")
         .set("revArray", new int[][]{{1}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir17")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir17").set("formula", "e+O=>e+O");
    model.component("comp1").physics("ptp").feature("eir17").set("mratio", "3.426200e-5");
    model.component("comp1").physics("ptp").feature("eir17").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir17")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.07"}, 
         {"0.2"}, 
         {"0.41"}, 
         {"0.68"}, 
         {"0.95"}, 
         {"1.22"}, 
         {"1.77"}, 
         {"2.6"}, 
         {"3.4"}, 
         {"5.4"}, 
         {"6.8"}, 
         {"9.5"}, 
         {"12.2"}, 
         {"17.7"}, 
         {"23.1"}});
    model.component("comp1").physics("ptp").feature("eir17")
         .set("ydata", new double[][]{{1.19E-20}, {1.19E-20}, {1.97E-20}, {2.78E-20}, {3.64E-20}, {4.33E-20}, {4.89E-20}, {5.72E-20}, {6.48E-20}, {6.9E-20}, {7.25E-20}, {7.3E-20}, {7.47E-20}, {8.2E-20}, {1.341E-19}, {2.43E-19}});
    model.component("comp1").physics("ptp").feature("eir17").active(true);
    model.component("comp1").physics("ptp").feature("eir17").label("17: e+O=>e+O");
    model.component("comp1").physics("ptp").feature("eir18").set("Species", new String[][]{{"e"}, {"O"}, {"O1D"}});
    model.component("comp1").physics("ptp").feature("eir18").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir18").set("revSpecies", new String[][]{{"e"}, {"O1D"}});
    model.component("comp1").physics("ptp").feature("eir18").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir18")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir18")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir18")
         .set("stoichArray", new int[][]{{0}, {0}, {-1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir18").set("formula", "e+O=>e+O1D");
    model.component("comp1").physics("ptp").feature("eir18").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir18").set("de", "1.968000e+0");
    model.component("comp1").physics("ptp").feature("eir18").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir18")
         .set("xdata", new double[][]{{1.968}, {1.97278}, {2.0272}, {2.04081}, {2.10884}, {2.31292}, {2.38094}, {2.58503}, {2.99319}, {3.33332}, {3.40135}, {3.80951}, {4.20407}, {4.62584}, {5.30611}, {5.44216}, {5.71427}, {5.98638}, {6.12243}, {6.66665}, {8.16324}, {10.2041}, {14.0136}, {16.0544}, {20.4081}, {25.8503}, {27.2108}, {28.5713}, {29.9319}, {31.2924}, {32.653}, {34.0135}, {35.374}, {36.7346}, {38.0951}, {39.4557}, {40.8162}, {47.6189}, {54.4216}, {61.2243}, {68.027}, {74.8297}, {81.6324}});
    model.component("comp1").physics("ptp").feature("eir18")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.61835E-24"}, 
         {"4.69189E-23"}, 
         {"6.27065E-23"}, 
         {"1.55873E-22"}, 
         {"4.92557E-22"}, 
         {"6.07994E-22"}, 
         {"9.38333E-22"}, 
         {"1.4852E-21"}, 
         {"1.82381E-21"}, 
         {"1.88041E-21"}, 
         {"2.15619E-21"}, 
         {"2.34151E-21"}, 
         {"2.46477E-21"}, 
         {"2.54541E-21"}, 
         {"2.55002E-21"}, 
         {"2.55244E-21"}, 
         {"2.54684E-21"}, 
         {"2.54126E-21"}, 
         {"2.50793E-21"}, 
         {"2.36534E-21"}, 
         {"2.15142E-21"}, 
         {"1.81755E-21"}, 
         {"1.62176E-21"}, 
         {"1.37591E-21"}, 
         {"1.14161E-21"}, 
         {"1.08633E-21"}, 
         {"1.03732E-21"}, 
         {"9.93853E-22"}, 
         {"9.54868E-22"}, 
         {"9.19421E-22"}, 
         {"8.85851E-22"}, 
         {"8.53637E-22"}, 
         {"8.25266E-22"}, 
         {"7.95328E-22"}, 
         {"7.65782E-22"}, 
         {"7.36755E-22"}, 
         {"6.12078E-22"}, 
         {"4.98162E-22"}, 
         {"3.94179E-22"}, 
         {"3.25808E-22"}, 
         {"2.6827E-22"}, 
         {"2.22122E-22"}});
    model.component("comp1").physics("ptp").feature("eir18").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir18").active(true);
    model.component("comp1").physics("ptp").feature("eir18").label("18: e+O=>e+O1D");
    model.component("comp1").physics("ptp").feature("eir19").set("Species", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir19").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir19").set("revSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir19").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir19")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir19")
         .set("revArray", new int[][]{{1}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir19")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir19").set("formula", "e+O=>e+O");
    model.component("comp1").physics("ptp").feature("eir19").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir19").set("de", "4.192000e+0");
    model.component("comp1").physics("ptp").feature("eir19").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir19")
         .set("xdata", new double[][]{{4.192}, {4.20407}, {4.2857}, {4.42175}, {4.48978}, {4.62584}, {4.89794}, {5.17005}, {5.71427}, {5.98638}, {6.53059}, {6.8027}, {7.89113}, {8.43535}, {8.97956}, {9.25167}, {9.52378}, {10.2041}, {11.5646}, {12.9251}, {13.7415}, {14.0136}, {14.2857}, {14.5578}, {14.8299}, {15.102}, {15.3741}, {15.6462}, {15.9183}, {16.1904}, {17.0068}, {18.3673}, {19.7278}, {21.0884}, {22.4489}, {24.4897}, {27.2108}, {29.9319}, {32.653}, {35.374}, {38.0951}, {40.8162}, {54.4216}, {68.027}, {81.6324}});
    model.component("comp1").physics("ptp").feature("eir19")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.5314E-25"}, 
         {"5.27675E-24"}, 
         {"1.83216E-23"}, 
         {"2.58067E-23"}, 
         {"4.14682E-23"}, 
         {"7.24623E-23"}, 
         {"1.0058E-22"}, 
         {"1.45801E-22"}, 
         {"1.63107E-22"}, 
         {"1.89134E-22"}, 
         {"1.9864E-22"}, 
         {"2.20635E-22"}, 
         {"2.25198E-22"}, 
         {"2.2709E-22"}, 
         {"2.27298E-22"}, 
         {"2.27118E-22"}, 
         {"2.25443E-22"}, 
         {"2.18648E-22"}, 
         {"2.10031E-22"}, 
         {"2.04524E-22"}, 
         {"2.02641E-22"}, 
         {"2.00709E-22"}, 
         {"1.98739E-22"}, 
         {"1.96707E-22"}, 
         {"1.94546E-22"}, 
         {"1.92141E-22"}, 
         {"1.88774E-22"}, 
         {"1.82029E-22"}, 
         {"1.78855E-22"}, 
         {"1.72053E-22"}, 
         {"1.66522E-22"}, 
         {"1.58119E-22"}, 
         {"1.5292E-22"}, 
         {"1.48215E-22"}, 
         {"1.39031E-22"}, 
         {"1.27993E-22"}, 
         {"1.1881E-22"}, 
         {"1.10808E-22"}, 
         {"1.03758E-22"}, 
         {"9.74923E-23"}, 
         {"9.1247E-23"}, 
         {"6.23757E-23"}, 
         {"3.92841E-23"}, 
         {"2.66209E-23"}});
    model.component("comp1").physics("ptp").feature("eir19").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir19").active(true);
    model.component("comp1").physics("ptp").feature("eir19").label("19: e+O=>e+O");
    model.component("comp1").physics("ptp").feature("eir20").set("Species", new String[][]{{"e"}, {"O"}, {"O_1p"}});
    model.component("comp1").physics("ptp").feature("eir20").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir20").set("revSpecies", new String[][]{{"e"}, {"O+"}});
    model.component("comp1").physics("ptp").feature("eir20").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir20")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir20")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir20")
         .set("stoichArray", new int[][]{{1}, {0}, {-1}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir20").set("formula", "e+O=>2e+O+");
    model.component("comp1").physics("ptp").feature("eir20").set("type", "Ionization");
    model.component("comp1").physics("ptp").feature("eir20").set("de", 13.618);
    model.component("comp1").physics("ptp").feature("eir20").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir20")
         .set("xdata", new String[][]{{"13.618"}, 
         {"20.0"}, 
         {"25.0"}, 
         {"30.0"}, 
         {"40.0"}, 
         {"60.0"}, 
         {"80.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"250.0"}, 
         {"300.0"}, 
         {"400.0"}});
    model.component("comp1").physics("ptp").feature("eir20")
         .set("ydata", new String[][]{{"0.0"}, 
         {"5.6E-21"}, 
         {"8.7E-21"}, 
         {"1.08E-20"}, 
         {"1.32E-20"}, 
         {"1.52E-20"}, 
         {"1.58E-20"}, 
         {"1.55E-20"}, 
         {"1.42E-20"}, 
         {"1.31E-20"}, 
         {"1.22E-20"}, 
         {"1.1E-20"}, 
         {"9.26E-21"}});
    model.component("comp1").physics("ptp").feature("eir20").active(true);
    model.component("comp1").physics("ptp").feature("eir20").label("20: e+O=>2e+O+");
    model.component("comp1").physics("ptp").feature("eir21").set("Species", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("eir21").set("fwdSpecies", new String[][]{{"e"}, {"Ar"}});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("ptp").feature("eir21").set("revSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("eir21").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir21")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir21")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir21")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir21").set("formula", "e+Ar=>e+Ar");
    model.component("comp1").physics("ptp").feature("eir21").set("mratio", "0.136E-04");
    model.component("comp1").physics("ptp").feature("eir21").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir21")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.001"}, 
         {"0.002"}, 
         {"0.003"}, 
         {"0.005"}, 
         {"0.007"}, 
         {"0.0085"}, 
         {"0.01"}, 
         {"0.015"}, 
         {"0.02"}, 
         {"0.03"}, 
         {"0.04"}, 
         {"0.05"}, 
         {"0.07"}, 
         {"0.1"}, 
         {"0.12"}, 
         {"0.15"}, 
         {"0.17"}, 
         {"0.2"}, 
         {"0.25"}, 
         {"0.3"}, 
         {"0.35"}, 
         {"0.4"}, 
         {"0.5"}, 
         {"0.7"}, 
         {"1.0"}, 
         {"1.2"}, 
         {"1.3"}, 
         {"1.5"}, 
         {"1.7"}, 
         {"1.9"}, 
         {"2.1"}, 
         {"2.2"}, 
         {"2.5"}, 
         {"2.8"}, 
         {"3.0"}, 
         {"3.3"}, 
         {"3.6"}, 
         {"4.0"}, 
         {"4.5"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}, 
         {"17.0"}, 
         {"20.0"}, 
         {"25.0"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"75.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("ptp").feature("eir21")
         .set("ydata", new double[][]{{7.5E-20}, {7.5E-20}, {7.1E-20}, {6.7E-20}, {6.1E-20}, {5.4E-20}, {5.05E-20}, {4.6E-20}, {3.75E-20}, {3.25E-20}, {2.5E-20}, {2.05E-20}, {1.73E-20}, {1.13E-20}, {5.9E-21}, {4.0E-21}, {2.3E-21}, {1.6E-21}, {1.03E-21}, {9.1E-22}, {1.53E-21}, {2.35E-21}, {3.3E-21}, {5.1E-21}, {8.6E-21}, {1.38E-20}, {1.66E-20}, {1.82E-20}, {2.1E-20}, {2.3E-20}, {2.5E-20}, {2.8E-20}, {2.9E-20}, {3.3E-20}, {3.8E-20}, {4.1E-20}, {4.5E-20}, {4.9E-20}, {5.4E-20}, {6.1E-20}, {6.7E-20}, {8.1E-20}, {9.6E-20}, {1.17E-19}, {1.5E-19}, {1.45E-19}, {1.37E-19}, {1.1E-19}, {9.2E-20}, {6.8E-20}, {5.5E-20}, {3.2E-20}, {2.15E-20}, {1.6E-20}, {1.1E-20}, {8.8E-21}, {6.0E-21}, {3.7E-21}, {2.6E-21}, {1.7E-21}, {9.8E-22}, {6.6E-22}, {3.5E-22}, {1.5E-22}, {8.8E-23}, {4.9E-23}, {4.9E-23}});
    model.component("comp1").physics("ptp").feature("eir21").active(true);
    model.component("comp1").physics("ptp").feature("eir21").label("21: e+Ar=>e+Ar");
    model.component("comp1").physics("ptp").feature("eir22").set("Species", new String[][]{{"e"}, {"Ar"}, {"Ars"}});
    model.component("comp1").physics("ptp").feature("eir22").set("fwdSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("eir22").set("revSpecies", new String[][]{{"e"}, {"Ars"}});
    model.component("comp1").physics("ptp").feature("eir22").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir22")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir22")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}});
    model.component("comp1").physics("ptp").feature("eir22")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {1}, {0}});
    model.component("comp1").physics("ptp").feature("eir22").set("formula", "e+Ar=>e+Ars");
    model.component("comp1").physics("ptp").feature("eir22").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir22").set("de", "11.50");
    model.component("comp1").physics("ptp").feature("eir22").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir22")
         .set("xdata", new String[][]{{"0.0"}, 
         {"11.5"}, 
         {"12.7"}, 
         {"13.7"}, 
         {"14.7"}, 
         {"15.9"}, 
         {"16.5"}, 
         {"17.5"}, 
         {"18.5"}, 
         {"19.9"}, 
         {"22.2"}, 
         {"24.7"}, 
         {"27.0"}, 
         {"30.0"}, 
         {"33.0"}, 
         {"35.3"}, 
         {"42.0"}, 
         {"48.0"}, 
         {"52.0"}, 
         {"70.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("ptp").feature("eir22")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"7.0E-22"}, 
         {"1.41E-21"}, 
         {"2.28E-21"}, 
         {"3.8E-21"}, 
         {"4.8E-21"}, 
         {"6.1E-21"}, 
         {"7.5E-21"}, 
         {"9.2E-21"}, 
         {"1.17E-20"}, 
         {"1.33E-20"}, 
         {"1.42E-20"}, 
         {"1.44E-20"}, 
         {"1.41E-20"}, 
         {"1.34E-20"}, 
         {"1.25E-20"}, 
         {"1.16E-20"}, 
         {"1.11E-20"}, 
         {"9.4E-21"}, 
         {"7.6E-21"}, 
         {"6.0E-21"}, 
         {"5.05E-21"}, 
         {"3.95E-21"}, 
         {"2.8E-21"}, 
         {"2.25E-21"}, 
         {"1.77E-21"}, 
         {"1.36E-21"}, 
         {"1.1E-21"}, 
         {"8.3E-22"}, 
         {"5.8E-22"}, 
         {"4.5E-22"}, 
         {"3.5E-22"}, 
         {"3.5E-22"}});
    model.component("comp1").physics("ptp").feature("eir22").set("gw", 12);
    model.component("comp1").physics("ptp").feature("eir22").active(true);
    model.component("comp1").physics("ptp").feature("eir22").label("22: e+Ar=>e+Ars");
    model.component("comp1").physics("ptp").feature("eir23").set("Species", new String[][]{{"e"}, {"Ars"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("eir23").set("fwdSpecies", new String[][]{{"e"}, {"Ars"}});
    model.component("comp1").physics("ptp").feature("eir23").set("revSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("eir23").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir23")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("ptp").feature("eir23")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir23")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {-1}, {0}});
    model.component("comp1").physics("ptp").feature("eir23").set("formula", "e+Ars=>e+Ar");
    model.component("comp1").physics("ptp").feature("eir23").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir23").set("de", "-11.50");
    model.component("comp1").physics("ptp").feature("eir23").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir23")
         .set("xdata", new String[][]{{"-11.5"}, 
         {"0.0"}, 
         {"1.1999999999999993"}, 
         {"2.1999999999999993"}, 
         {"3.1999999999999993"}, 
         {"4.4"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.399999999999999"}, 
         {"10.7"}, 
         {"13.2"}, 
         {"15.5"}, 
         {"18.5"}, 
         {"21.5"}, 
         {"23.799999999999997"}, 
         {"30.5"}, 
         {"36.5"}, 
         {"40.5"}, 
         {"58.5"}, 
         {"88.5"}, 
         {"138.5"}, 
         {"188.5"}, 
         {"288.5"}, 
         {"488.5"}, 
         {"688.5"}, 
         {"988.5"}, 
         {"1488.5"}, 
         {"1988.5"}, 
         {"2988.5"}, 
         {"4988.5"}, 
         {"6988.5"}, 
         {"9988.5"}, 
         {"999988.5"}});
    model.component("comp1").physics("ptp").feature("eir23")
         .set("ydata", new String[][]{{"-0.0"}, 
         {"0.0"}, 
         {"6.173611111111115E-22"}, 
         {"7.317045454545457E-22"}, 
         {"8.728125E-22"}, 
         {"1.1443181818181814E-21"}, 
         {"1.3199999999999998E-21"}, 
         {"1.4826388888888888E-21"}, 
         {"1.6517857142857143E-21"}, 
         {"1.8162698412698414E-21"}, 
         {"2.0228971962616822E-21"}, 
         {"2.073926767676768E-21"}, 
         {"2.061290322580645E-21"}, 
         {"1.945945945945946E-21"}, 
         {"1.803488372093023E-21"}, 
         {"1.6562324929971988E-21"}, 
         {"1.4344262295081967E-21"}, 
         {"1.2712328767123287E-21"}, 
         {"1.1876543209876544E-21"}, 
         {"9.373219373219375E-22"}, 
         {"7.156308851224105E-22"}, 
         {"5.415162454873646E-22"}, 
         {"4.465075154730327E-22"}, 
         {"3.4228769497400347E-22"}, 
         {"2.388263391334016E-22"}, 
         {"1.906318082788671E-22"}, 
         {"1.4921598381385938E-22"}, 
         {"1.1420893516963384E-22"}, 
         {"9.219679825664236E-23"}, 
         {"6.943282583235737E-23"}, 
         {"4.8444756272760683E-23"}, 
         {"3.756170852114187E-23"}, 
         {"2.920024695065992E-23"}, 
         {"2.916700208719067E-23"}});
    model.component("comp1").physics("ptp").feature("eir23").set("gw", 1);
    model.component("comp1").physics("ptp").feature("eir23").active(true);
    model.component("comp1").physics("ptp").feature("eir23").label("23: e+Ars=>e+Ar");
    model.component("comp1").physics("ptp").feature("eir24")
         .set("Species", new String[][]{{"e"}, {"Ar"}, {"Ar_1p"}});
    model.component("comp1").physics("ptp").feature("eir24").set("fwdSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("eir24").set("revSpecies", new String[][]{{"e"}, {"Ar+"}});
    model.component("comp1").physics("ptp").feature("eir24").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir24")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir24")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}});
    model.component("comp1").physics("ptp").feature("eir24")
         .set("stoichArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {1}});
    model.component("comp1").physics("ptp").feature("eir24").set("formula", "e+Ar=>2e+Ar+");
    model.component("comp1").physics("ptp").feature("eir24").set("type", "Ionization");
    model.component("comp1").physics("ptp").feature("eir24").set("de", "15.80");
    model.component("comp1").physics("ptp").feature("eir24").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir24")
         .set("xdata", new String[][]{{"0.0"}, 
         {"15.8"}, 
         {"16.0"}, 
         {"17.0"}, 
         {"18.0"}, 
         {"20.0"}, 
         {"22.0"}, 
         {"23.75"}, 
         {"25.0"}, 
         {"26.5"}, 
         {"30.0"}, 
         {"32.5"}, 
         {"35.0"}, 
         {"37.5"}, 
         {"40.0"}, 
         {"50.0"}, 
         {"55.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("ptp").feature("eir24")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"2.02E-22"}, 
         {"1.34E-21"}, 
         {"2.94E-21"}, 
         {"6.3E-21"}, 
         {"9.3E-21"}, 
         {"1.15E-20"}, 
         {"1.3E-20"}, 
         {"1.45E-20"}, 
         {"1.8E-20"}, 
         {"1.99E-20"}, 
         {"2.17E-20"}, 
         {"2.31E-20"}, 
         {"2.39E-20"}, 
         {"2.53E-20"}, 
         {"2.6E-20"}, 
         {"2.85E-20"}, 
         {"2.52E-20"}, 
         {"2.39E-20"}, 
         {"2.0E-20"}, 
         {"1.45E-20"}, 
         {"1.15E-20"}, 
         {"8.6E-21"}, 
         {"6.4E-21"}, 
         {"5.2E-21"}, 
         {"3.6E-21"}, 
         {"2.4E-21"}, 
         {"1.8E-21"}, 
         {"1.35E-21"}, 
         {"1.35E-21"}});
    model.component("comp1").physics("ptp").feature("eir24").active(true);
    model.component("comp1").physics("ptp").feature("eir24").label("24: e+Ar=>2e+Ar+");
    model.component("comp1").physics("ptp").feature("eir25")
         .set("Species", new String[][]{{"e"}, {"Ars"}, {"Ar_1p"}});
    model.component("comp1").physics("ptp").feature("eir25").set("fwdSpecies", new String[][]{{"e"}, {"Ars"}});
    model.component("comp1").physics("ptp").feature("eir25").set("revSpecies", new String[][]{{"e"}, {"Ar+"}});
    model.component("comp1").physics("ptp").feature("eir25").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir25")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("ptp").feature("eir25")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}});
    model.component("comp1").physics("ptp").feature("eir25")
         .set("stoichArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {1}});
    model.component("comp1").physics("ptp").feature("eir25").set("formula", "e+Ars=>2e+Ar+");
    model.component("comp1").physics("ptp").feature("eir25").set("type", "Ionization");
    model.component("comp1").physics("ptp").feature("eir25").set("de", 4.427);
    model.component("comp1").physics("ptp").feature("eir25").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("ptp").feature("eir25")
         .set("xdata", new String[][]{{"0.0"}, 
         {"4.427"}, 
         {"4.628"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.5"}, 
         {"15.5"}, 
         {"20.0"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"100.0"}, 
         {"200.0"}, 
         {"1000.0"}, 
         {"10000.0"}, 
         {"100000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("ptp").feature("eir25")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"1.849E-20"}, 
         {"3.1E-20"}, 
         {"5.8E-20"}, 
         {"6.9E-20"}, 
         {"7.6E-20"}, 
         {"8.0E-20"}, 
         {"8.2E-20"}, 
         {"8.35E-20"}, 
         {"7.8E-20"}, 
         {"7.0E-20"}, 
         {"5.4E-20"}, 
         {"3.8E-20"}, 
         {"2.05E-20"}, 
         {"1.2E-20"}, 
         {"3.5E-21"}, 
         {"6.1E-22"}, 
         {"1.08E-22"}, 
         {"1.08E-22"}});
    model.component("comp1").physics("ptp").feature("eir25").active(true);
    model.component("comp1").physics("ptp").feature("eir25").label("25: e+Ars=>2e+Ar+");
    model.component("comp1").physics("ptp").feature("eir26").set("Species", new String[][]{{"e"}, {"O_1m"}, {"O"}});
    model.component("comp1").physics("ptp").feature("eir26").set("fwdSpecies", new String[][]{{"e"}, {"O-"}});
    model.component("comp1").physics("ptp").feature("eir26").set("revSpecies", new String[][]{{"O"}, {"e"}});
    model.component("comp1").physics("ptp").feature("eir26").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("eir26")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir26")
         .set("revArray", new int[][]{{2}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir26")
         .set("stoichArray", new int[][]{{1}, {0}, {1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("eir26").set("formula", "e+O-=>O+e+e");
    model.component("comp1").physics("ptp").feature("eir26").set("type", "Ionization");
    model.component("comp1").physics("ptp").feature("eir26").set("de", "12.0");
    model.component("comp1").physics("ptp").feature("eir26")
         .set("kf", "5.47e-14[m^3/s]*N_A_const*exp(-2.98[V]/ptp.Te)*(ptp.Te/1[V])^0.324");
    model.component("comp1").physics("ptp").feature("eir26").active(true);
    model.component("comp1").physics("ptp").feature("eir26").label("26: e+O-=>O+e+e");
    model.component("comp1").physics("ptp").feature("rxn1").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("rxn1")
         .set("Species", new String[][]{{"O_1p"}, {"O2"}, {"O"}, {"O2_1p"}});
    model.component("comp1").physics("ptp").feature("rxn1").set("fwdSpecies", new String[][]{{"O+"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("rxn1").set("revSpecies", new String[][]{{"O"}, {"O2+"}});
    model.component("comp1").physics("ptp").feature("rxn1").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("rxn1")
         .set("fwdArray", new int[][]{{0}, {-1}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn1")
         .set("revArray", new int[][]{{0}, {0}, {1}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn1")
         .set("stoichArray", new int[][]{{0}, {-1}, {1}, {0}, {0}, {0}, {1}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn1").set("formula", "O++O2=>O+O2+");
    model.component("comp1").physics("ptp").feature("rxn1").set("kf", "2e-17[m^3/s]*N_A_const*(300[K]/ptp.T)^0.5");
    model.component("comp1").physics("ptp").feature("rxn1").active(true);
    model.component("comp1").physics("ptp").feature("rxn1").label("27: O++O2=>O+O2+");
    model.component("comp1").physics("ptp").feature("rxn2").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("rxn2")
         .set("Species", new String[][]{{"O_1m"}, {"O_1p"}, {"O"}});
    model.component("comp1").physics("ptp").feature("rxn2").set("fwdSpecies", new String[][]{{"O-"}, {"O+"}});
    model.component("comp1").physics("ptp").feature("rxn2").set("revSpecies", "O");
    model.component("comp1").physics("ptp").feature("rxn2").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("rxn2")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn2")
         .set("revArray", new int[][]{{0}, {0}, {2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn2")
         .set("stoichArray", new int[][]{{0}, {0}, {2}, {-1}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn2").set("formula", "O-+O+=>O+O");
    model.component("comp1").physics("ptp").feature("rxn2").set("kf", "4e-14[m^3/s]*N_A_const*(300[K]/ptp.T)^0.43");
    model.component("comp1").physics("ptp").feature("rxn2").active(true);
    model.component("comp1").physics("ptp").feature("rxn2").label("28: O-+O+=>O+O");
    model.component("comp1").physics("ptp").feature("rxn3").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("rxn3")
         .set("Species", new String[][]{{"O_1m"}, {"O2_1p"}, {"O"}});
    model.component("comp1").physics("ptp").feature("rxn3").set("fwdSpecies", new String[][]{{"O-"}, {"O2+"}});
    model.component("comp1").physics("ptp").feature("rxn3").set("revSpecies", "O");
    model.component("comp1").physics("ptp").feature("rxn3").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("rxn3")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn3")
         .set("revArray", new int[][]{{0}, {0}, {3}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn3")
         .set("stoichArray", new int[][]{{0}, {0}, {3}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn3").set("formula", "O-+O2+=>O+O+O");
    model.component("comp1").physics("ptp").feature("rxn3")
         .set("kf", "2.6e-14[m^3/s]*N_A_const*(300[K]/ptp.T)^0.44");
    model.component("comp1").physics("ptp").feature("rxn3").active(true);
    model.component("comp1").physics("ptp").feature("rxn3").label("29: O-+O2+=>O+O+O");
    model.component("comp1").physics("ptp").feature("rxn4").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("rxn4")
         .set("Species", new String[][]{{"O_1m"}, {"O2_1p"}, {"O"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("rxn4").set("fwdSpecies", new String[][]{{"O-"}, {"O2+"}});
    model.component("comp1").physics("ptp").feature("rxn4").set("revSpecies", new String[][]{{"O"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("rxn4").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("rxn4")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn4")
         .set("revArray", new int[][]{{0}, {1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn4")
         .set("stoichArray", new int[][]{{0}, {1}, {1}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn4").set("formula", "O-+O2+=>O+O2");
    model.component("comp1").physics("ptp").feature("rxn4")
         .set("kf", "2.6e-14[m^3/s]*N_A_const*(300[K]/ptp.T)^0.44");
    model.component("comp1").physics("ptp").feature("rxn4").active(true);
    model.component("comp1").physics("ptp").feature("rxn4").label("30: O-+O2+=>O+O2");
    model.component("comp1").physics("ptp").feature("rxn5").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("rxn5")
         .set("Species", new String[][]{{"O_1m"}, {"O"}, {"O2"}, {"e"}});
    model.component("comp1").physics("ptp").feature("rxn5").set("fwdSpecies", new String[][]{{"O-"}, {"O"}});
    model.component("comp1").physics("ptp").feature("rxn5").set("revSpecies", new String[][]{{"O2"}, {"e"}});
    model.component("comp1").physics("ptp").feature("rxn5").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("rxn5")
         .set("fwdArray", new int[][]{{0}, {0}, {-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn5")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn5")
         .set("stoichArray", new int[][]{{1}, {1}, {-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn5").set("formula", "O-+O=>O2+e");
    model.component("comp1").physics("ptp").feature("rxn5").set("kf", "3.0e-16[m^3/s]*N_A_const");
    model.component("comp1").physics("ptp").feature("rxn5").active(true);
    model.component("comp1").physics("ptp").feature("rxn5").label("31: O-+O=>O2+e");
    model.component("comp1").physics("ptp").feature("rxn6").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("rxn6")
         .set("Species", new String[][]{{"O_1m"}, {"Ar_1p"}, {"O"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("rxn6").set("fwdSpecies", new String[][]{{"O-"}, {"Ar+"}});
    model.component("comp1").physics("ptp").feature("rxn6").set("revSpecies", new String[][]{{"O"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("rxn6").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("rxn6")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}});
    model.component("comp1").physics("ptp").feature("rxn6")
         .set("revArray", new int[][]{{0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rxn6")
         .set("stoichArray", new int[][]{{0}, {0}, {1}, {-1}, {0}, {0}, {0}, {0}, {1}, {0}, {-1}});
    model.component("comp1").physics("ptp").feature("rxn6").set("formula", "O-+Ar+=>O+Ar");
    model.component("comp1").physics("ptp").feature("rxn6").set("kf", "4e-14[m^3/s]*N_A_const*(300[K]/ptp.T)^0.43");
    model.component("comp1").physics("ptp").feature("rxn6").active(true);
    model.component("comp1").physics("ptp").feature("rxn6").label("32: O-+Ar+=>O+Ar");
    model.component("comp1").physics("ptp").feature("e").set("specName", "e");
    model.component("comp1").physics("ptp").feature("e").set("specLabel", "e");
    model.component("comp1").physics("ptp").feature("e").set("sType", "electron");
    model.component("comp1").physics("ptp").feature("e").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("e").label("\u7269\u8d28: e");
    model.component("comp1").physics("ptp").feature("O2").set("specName", "O2");
    model.component("comp1").physics("ptp").feature("O2").set("specLabel", "O2");
    model.component("comp1").physics("ptp").feature("O2").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O2").set("PresetSpeciesData", "O2");
    model.component("comp1").physics("ptp").feature("O2").set("x0", "xO2");
    model.component("comp1").physics("ptp").feature("O2").label("\u7269\u8d28: O2");
    model.component("comp1").physics("ptp").feature("O").set("specName", "O");
    model.component("comp1").physics("ptp").feature("O").set("specLabel", "O");
    model.component("comp1").physics("ptp").feature("O").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O").set("PresetSpeciesData", "O");
    model.component("comp1").physics("ptp").feature("O").label("\u7269\u8d28: O");
    model.component("comp1").physics("ptp").feature("O_1m").set("specName", "O_1m");
    model.component("comp1").physics("ptp").feature("O_1m").set("specLabel", "O-");
    model.component("comp1").physics("ptp").feature("O_1m").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("O_1m").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O_1m").set("PresetSpeciesData", "O");
    model.component("comp1").physics("ptp").feature("O_1m").set("z", -1);
    model.component("comp1").physics("ptp").feature("O_1m").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("ptp").feature("O_1m").label("\u7269\u8d28: O-");
    model.component("comp1").physics("ptp").feature("O2a1Dg").set("specName", "O2a1Dg");
    model.component("comp1").physics("ptp").feature("O2a1Dg").set("specLabel", "O2a1Dg");
    model.component("comp1").physics("ptp").feature("O2a1Dg").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O2a1Dg").set("PresetSpeciesData", "O2");
    model.component("comp1").physics("ptp").feature("O2a1Dg").label("\u7269\u8d28: O2a1Dg");
    model.component("comp1").physics("ptp").feature("O1D").set("specName", "O1D");
    model.component("comp1").physics("ptp").feature("O1D").set("specLabel", "O1D");
    model.component("comp1").physics("ptp").feature("O1D").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O1D").set("PresetSpeciesData", "O");
    model.component("comp1").physics("ptp").feature("O1D").label("\u7269\u8d28: O1D");
    model.component("comp1").physics("ptp").feature("O2_1p").set("specName", "O2_1p");
    model.component("comp1").physics("ptp").feature("O2_1p").set("specLabel", "O2+");
    model.component("comp1").physics("ptp").feature("O2_1p").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("O2_1p").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O2_1p").set("PresetSpeciesData", "O2");
    model.component("comp1").physics("ptp").feature("O2_1p").set("z", 1);
    model.component("comp1").physics("ptp").feature("O2_1p").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("ptp").feature("O2_1p").label("\u7269\u8d28: O2+");
    model.component("comp1").physics("ptp").feature("O_1p").set("specName", "O_1p");
    model.component("comp1").physics("ptp").feature("O_1p").set("specLabel", "O+");
    model.component("comp1").physics("ptp").feature("O_1p").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("O_1p").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("O_1p").set("PresetSpeciesData", "O");
    model.component("comp1").physics("ptp").feature("O_1p").set("z", 1);
    model.component("comp1").physics("ptp").feature("O_1p").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("ptp").feature("O_1p").label("\u7269\u8d28: O+");
    model.component("comp1").physics("ptp").feature("Ar").set("specName", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").set("specLabel", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("Ar").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").label("\u7269\u8d28: Ar");
    model.component("comp1").physics("ptp").feature("Ars").set("specName", "Ars");
    model.component("comp1").physics("ptp").feature("Ars").set("specLabel", "Ars");
    model.component("comp1").physics("ptp").feature("Ars").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ars").label("\u7269\u8d28: Ars");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("specName", "Ar_1p");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("specLabel", "Ar+");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("z", 1);
    model.component("comp1").physics("ptp").feature("Ar_1p").label("\u7269\u8d28: Ar+");
    model.component("comp1").physics("ptp").feature("sr1").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr1").set("Species", new String[][]{{"Ar_1p"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("sr1").set("fwdSpecies", "Ar+");
    model.component("comp1").physics("ptp").feature("sr1").set("revSpecies", "Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr1")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}});
    model.component("comp1").physics("ptp").feature("sr1")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr1")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {-1}});

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature("sr1").active(true);
    model.component("comp1").physics("ptp").feature("sr1").label("1: Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr2").set("Species", new String[][]{{"Ars"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("sr2").set("fwdSpecies", "Ars");
    model.component("comp1").physics("ptp").feature("sr2").set("revSpecies", "Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr2")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("ptp").feature("sr2")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr2")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {-1}, {0}});
    model.component("comp1").physics("ptp").feature("sr2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature("sr2").active(true);
    model.component("comp1").physics("ptp").feature("sr2").label("2: Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr3").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr3").set("Species", new String[][]{{"O"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("sr3").set("fwdSpecies", "O");
    model.component("comp1").physics("ptp").feature("sr3").set("revSpecies", "O2");
    model.component("comp1").physics("ptp").feature("sr3").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr3")
         .set("fwdArray", new int[][]{{0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr3")
         .set("revArray", new double[][]{{0}, {0.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr3")
         .set("stoichArray", new double[][]{{0}, {0.5}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr3").set("formula", "O=>0.5O2");
    model.component("comp1").physics("ptp").feature("sr3").set("gammaf", 0.2);
    model.component("comp1").physics("ptp").feature("sr3").set("gammai", "0.0");
    model.component("comp1").physics("ptp").feature("sr3").set("ebari", "0.0");
    model.component("comp1").physics("ptp").feature("sr3").active(true);
    model.component("comp1").physics("ptp").feature("sr3").label("3: O=>0.5O2");
    model.component("comp1").physics("ptp").feature("sr4").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr4").set("Species", new String[][]{{"O2_1p"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("sr4").set("fwdSpecies", "O2+");
    model.component("comp1").physics("ptp").feature("sr4").set("revSpecies", "O2");
    model.component("comp1").physics("ptp").feature("sr4").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr4")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr4")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr4")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr4").set("formula", "O2+=>O2");
    model.component("comp1").physics("ptp").feature("sr4").set("gammai", 0.05);
    model.component("comp1").physics("ptp").feature("sr4").set("ebari", "4.0");
    model.component("comp1").physics("ptp").feature("sr4").active(true);
    model.component("comp1").physics("ptp").feature("sr4").label("4: O2+=>O2");
    model.component("comp1").physics("ptp").feature("sr5").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr5").set("Species", new String[][]{{"O_1m"}, {"O"}});
    model.component("comp1").physics("ptp").feature("sr5").set("fwdSpecies", "O-");
    model.component("comp1").physics("ptp").feature("sr5").set("revSpecies", "O");
    model.component("comp1").physics("ptp").feature("sr5").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr5")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr5")
         .set("revArray", new int[][]{{0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr5")
         .set("stoichArray", new int[][]{{0}, {0}, {1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr5").set("formula", "O-=>O");
    model.component("comp1").physics("ptp").feature("sr5").set("gammai", "0.0");
    model.component("comp1").physics("ptp").feature("sr5").set("ebari", "0.0");
    model.component("comp1").physics("ptp").feature("sr5").active(true);
    model.component("comp1").physics("ptp").feature("sr5").label("5: O-=>O");
    model.component("comp1").physics("ptp").feature("sr6").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr6").set("Species", new String[][]{{"O2a1Dg"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("sr6").set("fwdSpecies", "O2a1Dg");
    model.component("comp1").physics("ptp").feature("sr6").set("revSpecies", "O2");
    model.component("comp1").physics("ptp").feature("sr6").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr6")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr6")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr6")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr6").set("formula", "O2a1Dg=>O2");
    model.component("comp1").physics("ptp").feature("sr6").set("gammai", "0.0");
    model.component("comp1").physics("ptp").feature("sr6").set("ebari", "0.0");
    model.component("comp1").physics("ptp").feature("sr6").active(true);
    model.component("comp1").physics("ptp").feature("sr6").label("6: O2a1Dg=>O2");
    model.component("comp1").physics("ptp").feature("sr7").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr7").set("Species", new String[][]{{"O1D"}, {"O2"}});
    model.component("comp1").physics("ptp").feature("sr7").set("fwdSpecies", "O1D");
    model.component("comp1").physics("ptp").feature("sr7").set("revSpecies", "O2");
    model.component("comp1").physics("ptp").feature("sr7").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr7")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr7")
         .set("revArray", new double[][]{{0}, {0.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr7")
         .set("stoichArray", new double[][]{{0}, {0.5}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr7").set("formula", "O1D=>0.5O2");
    model.component("comp1").physics("ptp").feature("sr7").set("gammaf", 0.2);
    model.component("comp1").physics("ptp").feature("sr7").set("gammai", "0.0");
    model.component("comp1").physics("ptp").feature("sr7").set("ebari", "0.0");
    model.component("comp1").physics("ptp").feature("sr7").active(true);
    model.component("comp1").physics("ptp").feature("sr7").label("7: O1D=>0.5O2");
    model.component("comp1").physics("ptp").feature("sr8").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr8").set("Species", new String[][]{{"O_1p"}, {"O"}});
    model.component("comp1").physics("ptp").feature("sr8").set("fwdSpecies", "O+");
    model.component("comp1").physics("ptp").feature("sr8").set("revSpecies", "O");
    model.component("comp1").physics("ptp").feature("sr8").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr8").set("formula", "O+=>O");
    model.component("comp1").physics("ptp").feature("sr8").set("gammai", 0.05);
    model.component("comp1").physics("ptp").feature("sr8").set("ebari", "4.0");
    model.component("comp1").physics("ptp").feature("sr8").active(true);
    model.component("comp1").physics("ptp").feature("sr8").label("8: O+=>O");
    model.component("comp1").physics("ptp").feature("pes1").set("T", "300[K]");
    model.component("comp1").physics("ptp").feature("pes1").set("pA", "0.05[torr]");
    model.component("comp1").physics("ptp").feature("init1").set("neinit", "1E15[1/m^3]");
    model.component("comp1").physics("ptp").feature("init1").set("InitialPotential", "UserDefined");
    model.component("comp1").physics("ptp").feature("mct1").set("Source", "RF");
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "10[W]");
    model.component("comp1").physics("ptp").feature("in1").set("specNames", new String[][]{{"O2"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("in1").set("xIn", new String[][]{{"xO2"}, {"xAr"}});

    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 150);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("tper", "TimePeriodic");

    model.sol().create("sol1");
    model.sol("sol1").attach("std1");

    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").set("comp", "ptp_xdim");
    model.result().create("pg1", "PlotGroup1D");
    model.result().create("pg2", "PlotGroup1D");
    model.result().create("pg3", "PlotGroup1D");
    model.result().create("pg4", "PlotGroup1D");
    model.result().create("pg5", "PlotGroup1D");
    model.result().create("pg6", "PlotGroup1D");
    model.result().create("pg8", "PlotGroup1D");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "ptp.Teav");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", "ptp.Vav");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").create("lngr3", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", "ptp.Pcap_av");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").selection().all();
    model.result("pg4").feature("lngr2").set("expr", "ptp.Pcap_ions_av");
    model.result("pg4").feature("lngr3").set("xdata", "expr");
    model.result("pg4").feature("lngr3").selection().all();
    model.result("pg4").feature("lngr3").set("expr", "ptp.Pcap_ele_ions_av");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").create("lngr3", "LineGraph");
    model.result("pg5").create("lngr4", "LineGraph");
    model.result("pg5").create("lngr5", "LineGraph");
    model.result("pg5").create("lngr6", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "ptp.n_wO2_av");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("expr", "ptp.n_wO_av");
    model.result("pg5").feature("lngr3").set("xdata", "expr");
    model.result("pg5").feature("lngr3").selection().all();
    model.result("pg5").feature("lngr3").set("expr", "ptp.n_wO2a1Dg_av");
    model.result("pg5").feature("lngr4").set("xdata", "expr");
    model.result("pg5").feature("lngr4").selection().all();
    model.result("pg5").feature("lngr4").set("expr", "ptp.n_wO1D_av");
    model.result("pg5").feature("lngr5").set("xdata", "expr");
    model.result("pg5").feature("lngr5").selection().all();
    model.result("pg5").feature("lngr5").set("expr", "ptp.n_wAr_av");
    model.result("pg5").feature("lngr6").set("xdata", "expr");
    model.result("pg5").feature("lngr6").selection().all();
    model.result("pg5").feature("lngr6").set("expr", "ptp.n_wArs_av");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").create("lngr3", "LineGraph");
    model.result("pg6").create("lngr4", "LineGraph");
    model.result("pg6").create("lngr5", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("expr", "ptp.n_wO_1m_av");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").selection().all();
    model.result("pg6").feature("lngr2").set("expr", "ptp.n_wO2_1p_av");
    model.result("pg6").feature("lngr3").set("xdata", "expr");
    model.result("pg6").feature("lngr3").selection().all();
    model.result("pg6").feature("lngr3").set("expr", "ptp.n_wO_1p_av");
    model.result("pg6").feature("lngr4").set("xdata", "expr");
    model.result("pg6").feature("lngr4").selection().all();
    model.result("pg6").feature("lngr4").set("expr", "ptp.n_wAr_1p_av");
    model.result("pg6").feature("lngr5").set("xdata", "expr");
    model.result("pg6").feature("lngr5").selection().all();
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").create("lngr3", "LineGraph");
    model.result("pg8").create("lngr4", "LineGraph");
    model.result("pg8").create("lngr5", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").selection().all();
    model.result("pg8").feature("lngr1").set("expr", "ptp.n_wO_1m_av");
    model.result("pg8").feature("lngr2").set("xdata", "expr");
    model.result("pg8").feature("lngr2").selection().all();
    model.result("pg8").feature("lngr2").set("expr", "ptp.n_wO2_1p_av");
    model.result("pg8").feature("lngr3").set("xdata", "expr");
    model.result("pg8").feature("lngr3").selection().all();
    model.result("pg8").feature("lngr3").set("expr", "ptp.n_wO_1p_av");
    model.result("pg8").feature("lngr4").set("xdata", "expr");
    model.result("pg8").feature("lngr4").selection().all();
    model.result("pg8").feature("lngr4").set("expr", "ptp.n_wAr_1p_av");
    model.result("pg8").feature("lngr5").set("xdata", "expr");
    model.result("pg8").feature("lngr5").selection().all();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "ptp.mct1.V");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").selection().all();
    model.result("pg7").feature("lngr2").set("expr", "ptp.mct1.I");

    model.nodeGroup().create("grp1", "Physics", "ptp");
    model.nodeGroup("grp1").placeAfter(null);
    model.nodeGroup().create("grp2", "Physics", "ptp");
    model.nodeGroup("grp2").placeAfter(null);
    model.nodeGroup().create("grp3", "Physics", "ptp");
    model.nodeGroup("grp3").placeAfter(null);
    model.nodeGroup().create("grp4", "Physics", "ptp");
    model.nodeGroup("grp4").placeAfter(null);

    model.study("std1").feature("tper").set("ftplistmethod", "manual");
    model.study("std1").feature("tper").set("useparam", true);
    model.study("std1").feature("tper").set("pname", new String[]{"xO2"});
    model.study("std1").feature("tper").set("plistarr", new String[]{"0.9 0.5 0.1"});
    model.study("std1").feature("tper").set("punit", new String[]{""});

    model.sol("sol1").createAutoSequence("std1");

    model.study("std1").runNoGen();

    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg1").set("xlabel", "x \u5750\u6807 (cm)");
    model.result("pg1").set("ylabel", "\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (1/m<sup>3</sup>)");
    model.result("pg1").set("xlabelactive", false);
    model.result("pg1").set("ylabelactive", false);
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("xdataunit", "cm");
    model.result("pg1").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg1").feature("lngr1").set("resolution", "normal");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("xdataunit", "cm");
    model.result("pg2").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg2").feature("lngr1").set("resolution", "normal");
    model.result("pg3").label("\u7535\u52bf\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("xdataunit", "cm");
    model.result("pg3").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg3").feature("lngr1").set("resolution", "normal");
    model.result("pg4").label("\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabel", "Power Deposition (W/m<sup>3</sup>)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("xdataunit", "cm");
    model.result("pg4").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").set("legends", new String[]{"Electrons"});
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").feature("lngr2").set("xdataexpr", "x");
    model.result("pg4").feature("lngr2").set("xdataunit", "cm");
    model.result("pg4").feature("lngr2").set("xdatadescr", "x \u5750\u6807");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").set("legends", new String[]{"Ions"});
    model.result("pg4").feature("lngr2").set("resolution", "norefine");
    model.result("pg4").feature("lngr3").set("xdataexpr", "x");
    model.result("pg4").feature("lngr3").set("xdataunit", "cm");
    model.result("pg4").feature("lngr3").set("xdatadescr", "x \u5750\u6807");
    model.result("pg4").feature("lngr3").set("legend", true);
    model.result("pg4").feature("lngr3").set("legendmethod", "manual");
    model.result("pg4").feature("lngr3").set("legends", new String[]{"Electrons and ions"});
    model.result("pg4").feature("lngr3").set("resolution", "norefine");
    model.result("pg5").label("\u4e2d\u6027\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg5").set("looplevelinput", new String[]{"manual"});
    model.result("pg5").set("looplevel", new int[]{1});
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Neutral Species Number Density, Period Averaged (1/m<sup>3</sup>)");
    model.result("pg5").set("xlabel", "x \u5750\u6807 (cm)");
    model.result("pg5").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("xlabelactive", false);
    model.result("pg5").set("ylabelactive", false);
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("xdataunit", "cm");
    model.result("pg5").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").set("legends", new String[]{"wO2"});
    model.result("pg5").feature("lngr1").set("resolution", "normal");
    model.result("pg5").feature("lngr2").set("xdataexpr", "x");
    model.result("pg5").feature("lngr2").set("xdataunit", "cm");
    model.result("pg5").feature("lngr2").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").set("legends", new String[]{"wO"});
    model.result("pg5").feature("lngr2").set("resolution", "normal");
    model.result("pg5").feature("lngr3").set("xdataexpr", "x");
    model.result("pg5").feature("lngr3").set("xdataunit", "cm");
    model.result("pg5").feature("lngr3").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr3").set("legend", true);
    model.result("pg5").feature("lngr3").set("legendmethod", "manual");
    model.result("pg5").feature("lngr3").set("legends", new String[]{"wO2a1Dg"});
    model.result("pg5").feature("lngr3").set("resolution", "normal");
    model.result("pg5").feature("lngr4").set("xdataexpr", "x");
    model.result("pg5").feature("lngr4").set("xdataunit", "cm");
    model.result("pg5").feature("lngr4").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr4").set("legend", true);
    model.result("pg5").feature("lngr4").set("legendmethod", "manual");
    model.result("pg5").feature("lngr4").set("legends", new String[]{"wO1D"});
    model.result("pg5").feature("lngr4").set("resolution", "normal");
    model.result("pg5").feature("lngr5").set("xdataexpr", "x");
    model.result("pg5").feature("lngr5").set("xdataunit", "cm");
    model.result("pg5").feature("lngr5").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr5").set("legend", true);
    model.result("pg5").feature("lngr5").set("legendmethod", "manual");
    model.result("pg5").feature("lngr5").set("legends", new String[]{"wAr"});
    model.result("pg5").feature("lngr5").set("resolution", "normal");
    model.result("pg5").feature("lngr6").set("xdataexpr", "x");
    model.result("pg5").feature("lngr6").set("xdataunit", "cm");
    model.result("pg5").feature("lngr6").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr6").set("legend", true);
    model.result("pg5").feature("lngr6").set("legendmethod", "manual");
    model.result("pg5").feature("lngr6").set("legends", new String[]{"wArs"});
    model.result("pg5").feature("lngr6").set("resolution", "normal");
    model.result("pg6")
         .label("\u5e26\u7535\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747\uff0cxO2=0.9");
    model.result("pg6").set("looplevelinput", new String[]{"manual"});
    model.result("pg6").set("looplevel", new int[]{1});
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "xO2=0.9, xAr=0.1");
    model.result("pg6").set("xlabel", "x \u5750\u6807 (cm)");
    model.result("pg6").set("ylabel", "Number density (1/m<sup>3</sup>)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("xlabelactive", false);
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("xdataunit", "cm");
    model.result("pg6").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").set("legends", new String[]{"wO_1m"});
    model.result("pg6").feature("lngr1").set("resolution", "normal");
    model.result("pg6").feature("lngr2").set("xdataexpr", "x");
    model.result("pg6").feature("lngr2").set("xdataunit", "cm");
    model.result("pg6").feature("lngr2").set("xdatadescr", "x \u5750\u6807");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").set("legends", new String[]{"wO2_1p"});
    model.result("pg6").feature("lngr2").set("resolution", "normal");
    model.result("pg6").feature("lngr3").set("xdataexpr", "x");
    model.result("pg6").feature("lngr3").set("xdataunit", "cm");
    model.result("pg6").feature("lngr3").set("xdatadescr", "x \u5750\u6807");
    model.result("pg6").feature("lngr3").set("legend", true);
    model.result("pg6").feature("lngr3").set("legendmethod", "manual");
    model.result("pg6").feature("lngr3").set("legends", new String[]{"wO_1p"});
    model.result("pg6").feature("lngr3").set("resolution", "normal");
    model.result("pg6").feature("lngr4").set("xdataexpr", "x");
    model.result("pg6").feature("lngr4").set("xdataunit", "cm");
    model.result("pg6").feature("lngr4").set("xdatadescr", "x \u5750\u6807");
    model.result("pg6").feature("lngr4").set("legend", true);
    model.result("pg6").feature("lngr4").set("legendmethod", "manual");
    model.result("pg6").feature("lngr4").set("legends", new String[]{"wAr_1p"});
    model.result("pg6").feature("lngr4").set("resolution", "normal");
    model.result("pg6").feature("lngr5").set("xdataexpr", "x");
    model.result("pg6").feature("lngr5").set("xdataunit", "cm");
    model.result("pg6").feature("lngr5").set("xdatadescr", "x \u5750\u6807");
    model.result("pg6").feature("lngr5").set("legend", true);
    model.result("pg6").feature("lngr5").set("legendmethod", "manual");
    model.result("pg6").feature("lngr5").set("legends", new String[]{"ne"});
    model.result("pg6").feature("lngr5").set("resolution", "normal");
    model.result("pg8")
         .label("\u5e26\u7535\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747\uff0cxO2=0.1");
    model.result("pg8").set("looplevelinput", new String[]{"manual"});
    model.result("pg8").set("looplevel", new int[]{3});
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "xO2=0.1, xAr=0.9");
    model.result("pg8").set("xlabel", "x \u5750\u6807 (cm)");
    model.result("pg8").set("ylabel", "Number density (1/m<sup>3</sup>)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylog", true);
    model.result("pg8").set("xlabelactive", false);
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").set("xdataunit", "cm");
    model.result("pg8").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").set("legends", new String[]{"wO_1m"});
    model.result("pg8").feature("lngr1").set("resolution", "normal");
    model.result("pg8").feature("lngr2").set("xdataexpr", "x");
    model.result("pg8").feature("lngr2").set("xdataunit", "cm");
    model.result("pg8").feature("lngr2").set("xdatadescr", "x \u5750\u6807");
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").set("legends", new String[]{"wO2_1p"});
    model.result("pg8").feature("lngr2").set("resolution", "normal");
    model.result("pg8").feature("lngr3").set("xdataexpr", "x");
    model.result("pg8").feature("lngr3").set("xdataunit", "cm");
    model.result("pg8").feature("lngr3").set("xdatadescr", "x \u5750\u6807");
    model.result("pg8").feature("lngr3").set("legend", true);
    model.result("pg8").feature("lngr3").set("legendmethod", "manual");
    model.result("pg8").feature("lngr3").set("legends", new String[]{"wO_1p"});
    model.result("pg8").feature("lngr3").set("resolution", "normal");
    model.result("pg8").feature("lngr4").set("xdataexpr", "x");
    model.result("pg8").feature("lngr4").set("xdataunit", "cm");
    model.result("pg8").feature("lngr4").set("xdatadescr", "x \u5750\u6807");
    model.result("pg8").feature("lngr4").set("legend", true);
    model.result("pg8").feature("lngr4").set("legendmethod", "manual");
    model.result("pg8").feature("lngr4").set("legends", new String[]{"wAr_1p"});
    model.result("pg8").feature("lngr4").set("resolution", "normal");
    model.result("pg8").feature("lngr5").set("xdataexpr", "x");
    model.result("pg8").feature("lngr5").set("xdataunit", "cm");
    model.result("pg8").feature("lngr5").set("xdatadescr", "x \u5750\u6807");
    model.result("pg8").feature("lngr5").set("legend", true);
    model.result("pg8").feature("lngr5").set("legendmethod", "manual");
    model.result("pg8").feature("lngr5").set("legends", new String[]{"ne"});
    model.result("pg8").feature("lngr5").set("resolution", "normal");
    model.result("pg7").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp)");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("xlabel", "Period fraction");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7")
         .set("plotonsecyaxis", new String[][]{{"\u7ebf\u7ed3\u679c\u56fe 1", "on", "lngr1"}, {"\u7ebf\u7ed3\u679c\u56fe 2", "off", "lngr2"}});
    model.result("pg7").feature("lngr1").set("xdataexpr", "x1_ptp");
    model.result("pg7").feature("lngr1").set("xdataunit", "m");
    model.result("pg7").feature("lngr1").set("xdatadescr", "x1_ptp \u5750\u6807");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").set("legends", new String[]{"V"});
    model.result("pg7").feature("lngr1").set("resolution", "normal");
    model.result("pg7").feature("lngr2").set("xdataexpr", "x1_ptp");
    model.result("pg7").feature("lngr2").set("xdataunit", "m");
    model.result("pg7").feature("lngr2").set("xdatadescr", "x1_ptp \u5750\u6807");

    return model;
  }

  public static Model run5(Model model) {
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("legendmethod", "manual");
    model.result("pg7").feature("lngr2").set("legends", new String[]{"I"});
    model.result("pg7").feature("lngr2").set("resolution", "norefine");

    model.nodeGroup("grp1").label("\u7535\u5b50\u78b0\u649e\u53cd\u5e94");
    model.nodeGroup("grp1").add("xsec1");
    model.nodeGroup("grp1").add("xsec2");
    model.nodeGroup("grp1").add("xsec3");
    model.nodeGroup("grp1").add("eir1");
    model.nodeGroup("grp1").add("eir2");
    model.nodeGroup("grp1").add("eir3");
    model.nodeGroup("grp1").add("eir4");
    model.nodeGroup("grp1").add("eir5");
    model.nodeGroup("grp1").add("eir6");
    model.nodeGroup("grp1").add("eir7");
    model.nodeGroup("grp1").add("eir8");
    model.nodeGroup("grp1").add("eir9");
    model.nodeGroup("grp1").add("eir10");
    model.nodeGroup("grp1").add("eir11");
    model.nodeGroup("grp1").add("eir12");
    model.nodeGroup("grp1").add("eir13");
    model.nodeGroup("grp1").add("eir14");
    model.nodeGroup("grp1").add("eir15");
    model.nodeGroup("grp1").add("eir16");
    model.nodeGroup("grp1").add("eir17");
    model.nodeGroup("grp1").add("eir18");
    model.nodeGroup("grp1").add("eir19");
    model.nodeGroup("grp1").add("eir20");
    model.nodeGroup("grp1").add("eir21");
    model.nodeGroup("grp1").add("eir22");
    model.nodeGroup("grp1").add("eir23");
    model.nodeGroup("grp1").add("eir24");
    model.nodeGroup("grp1").add("eir25");
    model.nodeGroup("grp1").add("eir26");
    model.nodeGroup("grp2").label("\u91cd\u7269\u8d28\u53cd\u5e94");
    model.nodeGroup("grp2").add("rxn1");
    model.nodeGroup("grp2").add("rxn2");
    model.nodeGroup("grp2").add("rxn3");
    model.nodeGroup("grp2").add("rxn4");
    model.nodeGroup("grp2").add("rxn5");
    model.nodeGroup("grp2").add("rxn6");
    model.nodeGroup("grp3").label("\u7269\u8d28");
    model.nodeGroup("grp3").add("e");
    model.nodeGroup("grp3").add("O2");
    model.nodeGroup("grp3").add("O");
    model.nodeGroup("grp3").add("O_1m");
    model.nodeGroup("grp3").add("O2a1Dg");
    model.nodeGroup("grp3").add("O1D");
    model.nodeGroup("grp3").add("O2_1p");
    model.nodeGroup("grp3").add("O_1p");
    model.nodeGroup("grp3").add("Ar");
    model.nodeGroup("grp3").add("Ars");
    model.nodeGroup("grp3").add("Ar_1p");
    model.nodeGroup("grp4").label("\u8868\u9762\u53cd\u5e94");
    model.nodeGroup("grp4").add("sr1");
    model.nodeGroup("grp4").add("sr2");
    model.nodeGroup("grp4").add("sr3");
    model.nodeGroup("grp4").add("sr4");
    model.nodeGroup("grp4").add("sr5");
    model.nodeGroup("grp4").add("sr6");
    model.nodeGroup("grp4").add("sr7");
    model.nodeGroup("grp4").add("sr8");

    model.title("\u6c29/\u6c27\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u6a21\u578b");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3\u6c29/\u6c27\u6df7\u5408\u7269\u4e2d\u7684\u4e00\u7ef4\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\uff0c\u8ba8\u8bba\u4e86\u5bf9\u7535\u8d1f\u6027\u653e\u7535\u8fdb\u884c\u5efa\u6a21\u7684\u91cd\u8981\u65b9\u9762\u548c\u7b56\u7565\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("ccp_argon_oxygen.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    run5(model);
  }

}
