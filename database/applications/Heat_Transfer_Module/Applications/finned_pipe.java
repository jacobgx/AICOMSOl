/*
 * finned_pipe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:17 by COMSOL 6.3.0.290. */
public class finned_pipe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("th_i", "0.2[cm]");
    model.param().descr("th_i", "Pipe thickness");
    model.param().set("Rinn_i", "1.27[cm]");
    model.param().descr("Rinn_i", "Pipe inner radius");
    model.param().set("Rout_i", "Rinn_i+th_i");
    model.param().descr("Rout_i", "Pipe outer radius");
    model.param().set("L0_i", "1.27[cm]");
    model.param().descr("L0_i", "Pipe length");
    model.param().set("th_fins_i", "0.2[mm]");
    model.param().descr("th_fins_i", "Fins thickness");
    model.param().set("th_grvs_i", "0.3[cm]");
    model.param().descr("th_grvs_i", "Grooves thickness");
    model.param().set("th_innfins_i", "0.2[mm]");
    model.param().descr("th_innfins_i", "Inner fins thickness");
    model.param().set("th_inngrvs_i", "0.3[cm]");
    model.param().descr("th_inngrvs_i", "Inner grooves thickness");
    model.param().set("n_innfins_i", "8");
    model.param().descr("n_innfins_i", "Number of inner fins");
    model.param().set("n_inngrvs_i", "8");
    model.param().descr("n_inngrvs_i", "Number of inner grooves");
    model.param().set("L_fins_i", "1.47[cm]");
    model.param().descr("L_fins_i", "Fins length");
    model.param().set("L_grvs_i", "th_i/3");
    model.param().descr("L_grvs_i", "Grooves depth");
    model.param().set("L_innfins_i", "0.635[cm]");
    model.param().descr("L_innfins_i", "Inner fins length");
    model.param().set("L_inngrvs_i", "min(th_i/3,pi*Rinn_i/n_inngrvs_i)");
    model.param().descr("L_inngrvs_i", "Inner grooves depth");
    model.param().set("mfr0_inner", "0.25[kg/s]");
    model.param().descr("mfr0_inner", "Inlet mass flow rate, inner fluid");
    model.param().set("u0_outer", "1[m/s]");
    model.param().descr("u0_outer", "Inlet velocity, outer fluid");
    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "Air inlet temperature");
    model.param().set("pA0", "1[atm]");
    model.param().descr("pA0", "Inlet absolute pressure");
    model.param().set("dT", "10[K]");
    model.param().descr("dT", "Temperature difference between inner and outer fluids");
    model.param().set("rtol", "1e-3");
    model.param().descr("rtol", "Solver relative tolerance");
    model.param().set("u0_outer_min", "0.5[m/s]");
    model.param().descr("u0_outer_min", "Minimum air velocity");
    model.param().set("u0_outer_max", "6[m/s]");
    model.param().descr("u0_outer_max", "Maximum air velocity");
    model.param().set("u0_outer_steps", "2");
    model.param().descr("u0_outer_steps", "Number of steps for air velocity");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").label("Part: Inner, None");
    model.geom("part1").inputParam().set("Rinn", "1.27[cm]");
    model.geom("part1").inputParam().descr("Rinn", "Pipe inner radius");
    model.geom("part1").inputParam().set("L0", "1.27[cm]");
    model.geom("part1").inputParam().descr("L0", "Pipe length");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1").set("r", "Rinn");
    model.geom("part1").feature("cyl1").set("h", "L0");
    model.geom("part1").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part1").feature("cyl1").set("axistype", "x");
    model.geom("part1").run("cyl1");
    model.geom("part1").create("sel1", "ExplicitSelection");
    model.geom("part1").feature("sel1").label("Selection: Inner Fluid Domain");
    model.geom("part1").feature("sel1").selection("selection").set("cyl1", 1);
    model.geom("part1").run("sel1");
    model.geom("part1").create("sel2", "ExplicitSelection");
    model.geom("part1").feature("sel2").label("Selection: Inner Fluid Inlet");
    model.geom("part1").feature("sel2").selection("selection").init(2);
    model.geom("part1").feature("sel2").selection("selection").set("cyl1", 3);
    model.geom("part1").run("sel2");
    model.geom("part1").create("sel3", "ExplicitSelection");
    model.geom("part1").feature("sel3").label("Selection: Inner Fluid Outlet");
    model.geom("part1").feature("sel3").selection("selection").init(2);
    model.geom("part1").feature("sel3").selection("selection").set("cyl1", 4);
    model.geom("part1").run("sel3");
    model.geom("part1").create("unisel1", "UnionSelection");
    model.geom("part1").feature("unisel1").label("Selection: Flow Periodicity");
    model.geom("part1").feature("unisel1").set("entitydim", 2);
    model.geom("part1").feature("unisel1").set("input", new String[]{"sel2", "sel3"});
    model.geom("part1").run("unisel1");
    model.geom("part1").create("sel4", "ExplicitSelection");
    model.geom("part1").feature("sel4").label("Selection: Pressure Constraint");
    model.geom("part1").feature("sel4").selection("selection").init(0);
    model.geom("part1").feature("sel4").selection("selection").set("cyl1", 2);
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("Part: Inner, Straight Grooves");
    model.geom("part2").inputParam().set("Rinn", "1.27[cm]");
    model.geom("part2").inputParam().descr("Rinn", "Pipe inner radius");
    model.geom("part2").inputParam().set("L0", "1.27[cm]");
    model.geom("part2").inputParam().descr("L0", "Pipe length");
    model.geom("part2").inputParam().set("th_inngrvs", "0.3[cm]");
    model.geom("part2").inputParam().descr("th_inngrvs", "Inner grooves thickness");
    model.geom("part2").inputParam().set("n_inngrvs", "8");
    model.geom("part2").inputParam().descr("n_inngrvs", "Number of inner grooves");
    model.geom("part2").inputParam().set("L_inngrvs", "0.2[cm]/3");
    model.geom("part2").inputParam().descr("L_inngrvs", "Inner grooves depth");
    model.geom("part2").create("cyl1", "Cylinder");
    model.geom("part2").feature("cyl1").set("r", "Rinn");
    model.geom("part2").feature("cyl1").set("h", "L0");
    model.geom("part2").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part2").feature("cyl1").set("axistype", "x");
    model.geom("part2").run("cyl1");
    model.geom("part2").create("wp1", "WorkPlane");
    model.geom("part2").feature("wp1").set("unite", true);
    model.geom("part2").feature("wp1").set("planetype", "faceparallel");
    model.geom("part2").feature("wp1").selection("face").set("cyl1", 3);
    model.geom("part2").feature("wp1").geom().create("c1", "Circle");
    model.geom("part2").feature("wp1").geom().feature("c1").set("r", "th_inngrvs/2");
    model.geom("part2").feature("wp1").geom().feature("c1").set("pos", new String[]{"0", "Rinn-(th_inngrvs/2)/3"});
    model.geom("part2").feature("wp1").geom().run("c1");
    model.geom("part2").feature("wp1").geom().create("rot1", "Rotate");
    model.geom("part2").feature("wp1").geom().feature("rot1").selection("input").set("c1");
    model.geom("part2").feature("wp1").geom().feature("rot1").set("rot", "range(0,360/n_inngrvs,360-360/n_inngrvs)");
    model.geom("part2").run("wp1");
    model.geom("part2").feature().create("ext1", "Extrude");
    model.geom("part2").feature("ext1").setIndex("distance", "L0", 0);
    model.geom("part2").feature("ext1").set("reverse", true);
    model.geom("part2").run("ext1");
    model.geom("part2").create("cyl2", "Cylinder");
    model.geom("part2").feature("cyl2").set("r", "Rinn+2*L_inngrvs");
    model.geom("part2").feature("cyl2").set("h", "L0");
    model.geom("part2").feature("cyl2").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part2").feature("cyl2").set("axistype", "x");
    model.geom("part2").run("cyl2");
    model.geom("part2").create("dif1", "Difference");
    model.geom("part2").feature("dif1").selection("input").set("cyl2");
    model.geom("part2").feature("dif1").selection("input2").set("cyl1", "ext1");
    model.geom("part2").run("dif1");
    model.geom("part2").create("cyl3", "Cylinder");
    model.geom("part2").feature("cyl3").set("r", "Rinn+2*L_inngrvs");
    model.geom("part2").feature("cyl3").set("h", "L0");
    model.geom("part2").feature("cyl3").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part2").feature("cyl3").set("axistype", "x");
    model.geom("part2").run("cyl3");
    model.geom("part2").create("dif2", "Difference");
    model.geom("part2").feature("dif2").selection("input").set("cyl3");
    model.geom("part2").feature("dif2").selection("input2").set("dif1");
    model.geom("part2").run("dif2");
    model.geom("part2").create("sel1", "ExplicitSelection");
    model.geom("part2").feature("sel1").label("Selection: Inner Fluid Domain");
    model.geom("part2").feature("sel1").selection("selection").set("dif2", 1);
    model.geom("part2").run("sel1");
    model.geom("part2").create("sel2", "ExplicitSelection");
    model.geom("part2").feature("sel2").label("Selection: Inner Fluid Inlet");
    model.geom("part2").feature("sel2").selection("selection").init(2);
    model.geom("part2").feature("sel2").selection("selection").set("dif2", 1);
    model.geom("part2").run("sel2");
    model.geom("part2").create("sel3", "ExplicitSelection");
    model.geom("part2").feature("sel3").label("Selection: Inner Fluid Outlet");
    model.geom("part2").feature("sel3").selection("selection").init(2);
    model.geom("part2").feature("sel3").selection("selection").set("dif2", 26);
    model.geom("part2").run("sel3");
    model.geom("part2").create("unisel1", "UnionSelection");
    model.geom("part2").feature("unisel1").label("Inner Part: Straight Grooves");
    model.geom("part2").feature("unisel1").set("entitydim", 2);
    model.geom("part2").feature("unisel1").set("input", new String[]{"sel2", "sel3"});
    model.geom("part2").run("unisel1");
    model.geom("part2").create("sel4", "ExplicitSelection");
    model.geom("part2").feature("sel4").selection("selection").init(0);
    model.geom("part2").feature("sel4").selection("selection").set("dif2", 37);
    model.geom("part2").feature("sel4").label("Selection: Pressure Constraint");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").label("Part: Inner, Help");
    model.geom("part3").create("pi1", "PartInstance");
    model.geom("part3").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part3").feature("pi1").set("part", "part2");
    model.geom("part3").selection().create("csel1", "CumulativeSelection");
    model.geom("part3").selection("csel1").label("Inner Fluid Domain");
    model.geom("part3").feature("pi1").setEntry("selcontributetodom", "pi1_sel1", "csel1");
    model.geom("part3").selection().create("csel2", "CumulativeSelection");
    model.geom("part3").selection("csel2").label("Inner Fluid Inlet");
    model.geom("part3").feature("pi1").setEntry("selcontributetobnd", "pi1_sel2", "csel2");
    model.geom("part3").selection().create("csel3", "CumulativeSelection");
    model.geom("part3").selection("csel3").label("Inner Fluid Outlet");
    model.geom("part3").feature("pi1").setEntry("selcontributetobnd", "pi1_sel3", "csel3");
    model.geom("part3").selection().create("csel4", "CumulativeSelection");
    model.geom("part3").selection("csel4").label("Flow Periodicity");
    model.geom("part3").feature("pi1").setEntry("selcontributetobnd", "pi1_unisel1", "csel4");
    model.geom("part3").selection().create("csel5", "CumulativeSelection");
    model.geom("part3").selection("csel5").label("Pressure Constraint");
    model.geom("part3").feature("pi1").setEntry("selkeeppnt", "pi1_sel4", false);
    model.geom("part3").feature("pi1").setEntry("selcontributetopnt", "pi1_sel4", "csel5");
    model.geom("part3").run("pi1");
    model.geom("part3").run("pi1");
    model.geom().create("part4", "Part", 3);
    model.geom("part4").geomRep("comsol");
    model.geom("part4").label("Part: Outer, None");
    model.geom("part4").inputParam().set("Rout", "1.47[cm]");
    model.geom("part4").inputParam().descr("Rout", "Pipe outer radius");
    model.geom("part4").inputParam().set("L0", "1.27[cm]");
    model.geom("part4").inputParam().descr("L0", "Pipe length");
    model.geom("part4").create("cyl1", "Cylinder");
    model.geom("part4").feature("cyl1").set("r", "Rout");
    model.geom("part4").feature("cyl1").set("h", "L0");
    model.geom("part4").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part4").feature("cyl1").set("axistype", "x");
    model.geom("part4").run("cyl1");
    model.geom("part4").create("pi1", "PartInstance");
    model.geom("part4").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part4").feature("pi1").set("part", "part3");
    model.geom("part4").run("pi1");
    model.geom("part4").selection().create("csel1", "CumulativeSelection");
    model.geom("part4").selection("csel1").label("Inner Fluid Domain");
    model.geom("part4").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.geom("part4").selection().create("csel2", "CumulativeSelection");
    model.geom("part4").selection("csel2").label("Inner Fluid Inlet");
    model.geom("part4").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.geom("part4").selection().create("csel3", "CumulativeSelection");
    model.geom("part4").selection("csel3").label("Inner Fluid Outlet");
    model.geom("part4").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.geom("part4").selection().create("csel4", "CumulativeSelection");
    model.geom("part4").selection("csel4").label("Flow Periodicity");
    model.geom("part4").feature("pi1").setEntry("selcontributetobnd", "pi1_csel4.bnd", "csel4");
    model.geom("part4").create("co1", "Compose");
    model.geom("part4").feature("co1").set("formula", "cyl1-pi1+pi1");
    model.geom("part4").run("co1");
    model.geom("part4").create("sel1", "ExplicitSelection");
    model.geom("part4").feature("sel1").label("Selection: Temperature Periodicity");
    model.geom("part4").feature("sel1").selection("selection").init(2);
    model.geom("part4").feature("sel1").selection("selection").set("co1", 1, 4, 31, 32);
    model.geom("part4").run("sel1");
    model.geom("part4").create("sel2", "ExplicitSelection");
    model.geom("part4").feature("sel2").label("Selection: Exterior Fins");
    model.geom("part4").feature("sel2").selection("selection").init(2);
    model.geom("part4").run("sel2");
    model.geom("part4").create("sel3", "ExplicitSelection");
    model.geom("part4").feature("sel3").label("Selection: Composite Faces");
    model.geom("part4").feature("sel3").selection("selection").init(2);
    model.geom("part4").selection().create("csel5", "CumulativeSelection");
    model.geom("part4").selection("csel5").label("Pressure Constraint");
    model.geom("part4").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel5");
    model.geom("part4").run("sel3");
    model.geom().create("part5", "Part", 3);
    model.geom("part5").geomRep("comsol");
    model.geom("part5").label("Part: Outer, Disk-Stacked Blades");
    model.geom("part5").inputParam().set("Rout", "1.47[cm]");
    model.geom("part5").inputParam().descr("Rout", "Pipe outer radius");
    model.geom("part5").inputParam().set("L0", "1.27[cm]");
    model.geom("part5").inputParam().descr("L0", "Pipe length");
    model.geom("part5").inputParam().set("L_fins", "1.47[cm]");
    model.geom("part5").inputParam().descr("L_fins", "Fins length");
    model.geom("part5").create("cyl1", "Cylinder");
    model.geom("part5").feature("cyl1").set("r", "Rout");
    model.geom("part5").feature("cyl1").set("h", "L0");
    model.geom("part5").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part5").feature("cyl1").set("axistype", "x");
    model.geom("part5").run("cyl1");
    model.geom("part5").create("wp1", "WorkPlane");
    model.geom("part5").feature("wp1").set("unite", true);
    model.geom("part5").feature("wp1").set("planetype", "faceparallel");
    model.geom("part5").feature("wp1").selection("face").set("cyl1", 3);
    model.geom("part5").feature("wp1").set("offset", "-L0/2");
    model.geom("part5").feature("wp1").geom().create("c1", "Circle");
    model.geom("part5").feature("wp1").geom().feature("c1").set("r", "Rout+L_fins");
    model.geom("part5").feature("wp1").geom().run("c1");
    model.geom("part5").feature("wp1").geom().create("c2", "Circle");
    model.geom("part5").feature("wp1").geom().feature("c2").set("r", "Rout");
    model.geom("part5").feature("wp1").geom().run("c2");
    model.geom("part5").feature("wp1").geom().create("dif1", "Difference");
    model.geom("part5").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.geom("part5").feature("wp1").geom().feature("dif1").selection("input2").set("c2");
    model.geom("part5").feature("wp1").geom().run("dif1");
    model.geom("part5").run("wp1");
    model.geom("part5").create("pi1", "PartInstance");
    model.geom("part5").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part5").feature("pi1").set("part", "part3");
    model.geom("part5").run("pi1");
    model.geom("part5").selection().create("csel1", "CumulativeSelection");
    model.geom("part5").selection("csel1").label("Inner Fluid Domain");
    model.geom("part5").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.geom("part5").selection().create("csel2", "CumulativeSelection");
    model.geom("part5").selection("csel2").label("Inner Fluid Inlet");
    model.geom("part5").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.geom("part5").selection().create("csel3", "CumulativeSelection");
    model.geom("part5").selection("csel3").label("Inner Fluid Outlet");
    model.geom("part5").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.geom("part5").selection().create("csel4", "CumulativeSelection");
    model.geom("part5").selection("csel4").label("Flow Periodicity");
    model.geom("part5").feature("pi1").setEntry("selcontributetobnd", "pi1_csel4.bnd", "csel4");
    model.geom("part5").create("co1", "Compose");
    model.geom("part5").feature("co1").set("formula", "cyl1-pi1+cyl1+wp1+pi1");
    model.geom("part5").run("co1");
    model.geom("part5").create("sel1", "ExplicitSelection");
    model.geom("part5").feature("sel1").label("Selection: Temperature Periodicity");
    model.geom("part5").feature("sel1").selection("selection").init(2);
    model.geom("part5").feature("sel1").selection("selection").set("co1", 1, 4, 36, 37);
    model.geom("part5").run("sel1");
    model.geom("part5").create("sel2", "ExplicitSelection");
    model.geom("part5").feature("sel2").label("Selection: Exterior Fins");
    model.geom("part5").feature("sel2").selection("selection").init(2);
    model.geom("part5").feature("sel2").selection("selection").set("co1", 31);
    model.geom("part5").run("sel2");
    model.geom("part5").create("sel3", "ExplicitSelection");
    model.geom("part5").feature("sel3").label("Selection: Composite Faces");
    model.geom("part5").feature("sel3").selection("selection").init(2);
    model.geom("part5").selection().create("csel5", "CumulativeSelection");
    model.geom("part5").selection("csel5").label("Pressure Constraint");
    model.geom("part5").feature("pi1").setEntry("selkeeppnt", "pi1_csel5.pnt", false);
    model.geom("part5").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel5");
    model.geom("part5").run("sel3");
    model.geom().create("part6", "Part", 3);
    model.geom("part6").geomRep("comsol");
    model.geom("part6").label("Part: Outer, Circular Grooves");
    model.geom("part6").inputParam().set("Rout", "1.47[cm]");
    model.geom("part6").inputParam().descr("Rout", "Pipe outer radius");
    model.geom("part6").inputParam().set("L0", "1.27[cm]");
    model.geom("part6").inputParam().descr("L0", "Pipe length");
    model.geom("part6").inputParam().set("th_grvs", "0.3[cm]");
    model.geom("part6").inputParam().descr("th_grvs", "Fins thickness");
    model.geom("part6").inputParam().set("L_grvs", "0.2[cm]/3");
    model.geom("part6").inputParam().descr("L_grvs", "Grooves depth");
    model.geom("part6").create("cyl1", "Cylinder");
    model.geom("part6").feature("cyl1").set("r", "Rout");
    model.geom("part6").feature("cyl1").set("h", "L0");
    model.geom("part6").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part6").feature("cyl1").set("axistype", "x");
    model.geom("part6").run("cyl1");
    model.geom("part6").create("cyl2", "Cylinder");
    model.geom("part6").feature("cyl2").set("r", "Rout");
    model.geom("part6").feature("cyl2").set("h", "th_grvs");
    model.geom("part6").feature("cyl2").set("pos", new String[]{"-th_grvs/2", "0", "0"});
    model.geom("part6").feature("cyl2").set("axistype", "x");
    model.geom("part6").feature("cyl2").setIndex("layername", "Layer 1", 0);
    model.geom("part6").feature("cyl2").setIndex("layer", "L_grvs", 0);
    model.geom("part6").run("cyl2");
    model.geom("part6").create("del1", "Delete");
    model.geom("part6").feature("del1").selection("input").init(3);
    model.geom("part6").feature("del1").selection("input").set("cyl2", 3);
    model.geom("part6").run("del1");
    model.geom("part6").create("dif1", "Difference");
    model.geom("part6").feature("dif1").selection("input").set("cyl1");
    model.geom("part6").feature("dif1").selection("input2").set("del1");
    model.geom("part6").run("dif1");
    model.geom("part6").create("pi1", "PartInstance");
    model.geom("part6").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part6").feature("pi1").set("part", "part3");
    model.geom("part6").run("pi1");
    model.geom("part6").selection().create("csel1", "CumulativeSelection");
    model.geom("part6").selection("csel1").label("Inner Fluid Domain");
    model.geom("part6").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.geom("part6").selection().create("csel2", "CumulativeSelection");
    model.geom("part6").selection("csel2").label("Inner Fluid Inlet");
    model.geom("part6").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.geom("part6").selection().create("csel3", "CumulativeSelection");
    model.geom("part6").selection("csel3").label("Inner Fluid Outlet");
    model.geom("part6").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.geom("part6").selection().create("csel4", "CumulativeSelection");
    model.geom("part6").selection("csel4").label("Flow Periodicity");
    model.geom("part6").feature("pi1").setEntry("selcontributetobnd", "pi1_csel4.bnd", "csel4");
    model.geom("part6").create("co1", "Compose");
    model.geom("part6").feature("co1").set("formula", "dif1-pi1+pi1");
    model.geom("part6").run("co1");
    model.geom("part6").create("sel1", "ExplicitSelection");
    model.geom("part6").feature("sel1").label("Selection: Temperature Periodicity");
    model.geom("part6").feature("sel1").selection("selection").init(2);
    model.geom("part6").feature("sel1").selection("selection").set("co1", 1, 4, 47, 48);
    model.geom("part6").run("sel1");
    model.geom("part6").create("sel2", "ExplicitSelection");
    model.geom("part6").feature("sel2").label("Selection: Exterior Fins");
    model.geom("part6").feature("sel2").selection("selection").init(2);
    model.geom("part6").run("sel2");
    model.geom("part6").create("sel3", "ExplicitSelection");
    model.geom("part6").feature("sel3").label("Selection: Composite Faces");
    model.geom("part6").feature("sel3").selection("selection").init(2);
    model.geom("part6").selection().create("csel5", "CumulativeSelection");
    model.geom("part6").selection("csel5").label("Pressure Constraint");
    model.geom("part6").feature("pi1").setEntry("selkeeppnt", "pi1_csel5.pnt", false);
    model.geom("part6").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel5");
    model.geom("part6").run("sel3");
    model.geom().create("part7", "Part", 3);
    model.geom("part7").geomRep("comsol");
    model.geom("part7").label("Part: Outer, Helical Blade");
    model.geom("part7").inputParam().set("Rout", "1.47[cm]");
    model.geom("part7").inputParam().descr("Rout", "Pipe outer radius");
    model.geom("part7").inputParam().set("L0", "1.27[cm]");
    model.geom("part7").inputParam().descr("L0", "Pipe length");
    model.geom("part7").inputParam().set("L_fins", "1.47[cm]");
    model.geom("part7").inputParam().descr("L_fins", "Fins length");
    model.geom("part7").create("cyl1", "Cylinder");
    model.geom("part7").feature("cyl1").set("r", "Rout");
    model.geom("part7").feature("cyl1").set("h", "L0");
    model.geom("part7").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part7").feature("cyl1").set("axistype", "x");
    model.geom("part7").run("cyl1");
    model.geom("part7").create("ps1", "ParametricSurface");
    model.geom("part7").feature("ps1").set("parmin1", "-L0/2");
    model.geom("part7").feature("ps1").set("parmax1", "L0/2");
    model.geom("part7").feature("ps1").set("parmax2", "L_fins");
    model.geom("part7").feature("ps1").set("coord", new String[]{"s1", "(Rout+s2)*cos(2*pi*s1/L0[1/m])", ""});
    model.geom("part7").feature("ps1").setIndex("coord", "(Rout+s2)*sin(2*pi*s1/L0[1/m])", 2);
    model.geom("part7").feature("ps1").set("rtol", "1e-9");
    model.geom("part7").feature("ps1").set("maxknots", 1000);
    model.geom("part7").run("ps1");
    model.geom("part7").create("pi1", "PartInstance");
    model.geom("part7").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part7").feature("pi1").set("part", "part3");
    model.geom("part7").run("pi1");
    model.geom("part7").selection().create("csel1", "CumulativeSelection");
    model.geom("part7").selection("csel1").label("Inner Fluid Domain");
    model.geom("part7").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.geom("part7").selection().create("csel2", "CumulativeSelection");
    model.geom("part7").selection("csel2").label("Inner Fluid Inlet");
    model.geom("part7").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.geom("part7").selection().create("csel3", "CumulativeSelection");
    model.geom("part7").selection("csel3").label("Inner Fluid Outlet");
    model.geom("part7").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.geom("part7").selection().create("csel4", "CumulativeSelection");
    model.geom("part7").selection("csel4").label("Flow Periodicity");
    model.geom("part7").feature("pi1").setEntry("selcontributetobnd", "pi1_csel4.bnd", "csel4");
    model.geom("part7").create("uni1", "Union");
    model.geom("part7").feature("uni1").selection("input").set("cyl1", "ps1");
    model.geom("part7").feature("uni1").set("repairtoltype", "relative");
    model.geom("part7").run("uni1");
    model.geom("part7").create("co1", "Compose");
    model.geom("part7").feature("co1").set("formula", "uni1-pi1+pi1");
    model.geom("part7").run("co1");
    model.geom("part7").create("sel1", "ExplicitSelection");
    model.geom("part7").feature("sel1").label("Selection: Temperature Periodicity");
    model.geom("part7").feature("sel1").selection("selection").init(2);
    model.geom("part7").feature("sel1").selection("selection").set("co1", 2, 6, 16, 17);
    model.geom("part7").run("sel1");
    model.geom("part7").create("sel2", "ExplicitSelection");
    model.geom("part7").feature("sel2").label("Selection: Exterior Fins");
    model.geom("part7").feature("sel2").selection("selection").init(2);
    model.geom("part7").feature("sel2").selection("selection").set("co1", 1);
    model.geom("part7").run("sel2");
    model.geom("part7").create("sel3", "ExplicitSelection");
    model.geom("part7").feature("sel3").label("Selection: Composite Faces");
    model.geom("part7").feature("sel3").selection("selection").init(2);
    model.geom("part7").selection().create("csel5", "CumulativeSelection");
    model.geom("part7").selection("csel5").label("Pressure Constraint");
    model.geom("part7").feature("pi1").setEntry("selkeeppnt", "pi1_csel5.pnt", false);
    model.geom("part7").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel5");
    model.geom("part7").run("sel3");
    model.geom().create("part8", "Part", 3);
    model.geom("part8").geomRep("comsol");
    model.geom("part8").label("Part: Outer, Helical Grooves");
    model.geom("part8").inputParam().set("Rout", "1.47[cm]");
    model.geom("part8").inputParam().descr("Rout", "Pipe outer radius");
    model.geom("part8").inputParam().set("L0", "1.27[cm]");
    model.geom("part8").inputParam().descr("L0", "Pipe length");
    model.geom("part8").inputParam().set("L_fins", "1.47[cm]");

    return model;
  }

  public static Model run2(Model model) {
    model.geom("part8").inputParam().descr("L_fins", "Fins length");
    model.geom("part8").inputParam().set("th_grvs", "0.3[cm]");
    model.geom("part8").inputParam().descr("th_grvs", "Fins thickness");
    model.geom("part8").inputParam().set("L_grvs", "0.2[cm]/3");
    model.geom("part8").inputParam().descr("L_grvs", "Grooves depth");
    model.geom("part8").create("cyl1", "Cylinder");
    model.geom("part8").feature("cyl1").set("r", "Rout");
    model.geom("part8").feature("cyl1").set("h", "L0");
    model.geom("part8").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.geom("part8").feature("cyl1").set("axistype", "x");
    model.geom("part8").feature("cyl1").setIndex("layername", "Layer 1", 0);
    model.geom("part8").feature("cyl1").setIndex("layer", "L0/4", 0);
    model.geom("part8").feature("cyl1").set("layerside", false);
    model.geom("part8").feature("cyl1").set("layerbottom", true);
    model.geom("part8").feature("cyl1").set("layertop", true);
    model.geom("part8").run("cyl1");
    model.geom("part8").create("pc1", "ParametricCurve");
    model.geom("part8").feature("pc1").set("parmin", "-L0");
    model.geom("part8").feature("pc1").set("parmax", "L0");
    model.geom("part8").feature("pc1").set("coord", new String[]{"s", "Rout*cos(2*pi*s/L0[1/m])", ""});
    model.geom("part8").feature("pc1").setIndex("coord", "Rout*sin(2*pi*s/L0[1/m])", 2);
    model.geom("part8").run("pc1");
    model.geom("part8").create("wp1", "WorkPlane");
    model.geom("part8").feature("wp1").set("unite", true);
    model.geom("part8").feature("wp1").set("planetype", "coordinates");
    model.geom("part8").feature("wp1").setIndex("genpoints", "-L0", 0, 0);
    model.geom("part8").feature("wp1").setIndex("genpoints", "Rout", 0, 1);
    model.geom("part8").feature("wp1").setIndex("genpoints", "-L0", 1, 0);
    model.geom("part8").feature("wp1").setIndex("genpoints", "Rout+1", 1, 1);
    model.geom("part8").feature("wp1").setIndex("genpoints", "-L0+1", 2, 0);
    model.geom("part8").feature("wp1").setIndex("genpoints", "Rout", 2, 1);
    model.geom("part8").run("wp1");
    model.geom("part8").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("part8").feature("wp1").geom().feature("r1").set("size", new String[]{"2*L_grvs", "th_grvs"});
    model.geom("part8").feature("wp1").geom().feature("r1").set("pos", new String[]{"-L_grvs", "-th_grvs/2"});
    model.geom("part8").feature("wp1").geom().run("r1");
    model.geom("part8").run("wp1");
    model.geom("part8").create("swe1", "Sweep");
    model.geom("part8").feature("swe1").set("includefinal", false);
    model.geom("part8").feature("swe1").set("crossfaces", true);
    model.geom("part8").feature("swe1").set("manualdir", false);
    model.geom("part8").feature("swe1").selection("enttosweep").set("wp1", 1);
    model.geom("part8").feature("swe1").selection("edge").set("pc1", 1);
    model.geom("part8").feature("swe1").set("keep", false);
    model.geom("part8").feature("swe1").set("twisting", "curvature");
    model.geom("part8").run("swe1");
    model.geom("part8").create("spl1", "Split");
    model.geom("part8").feature("spl1").selection("input").set("cyl1");
    model.geom("part8").run("spl1");
    model.geom("part8").create("dif1", "Difference");
    model.geom("part8").feature("dif1").selection("input").set("spl1");
    model.geom("part8").feature("dif1").selection("input2").set("swe1");
    model.geom("part8").run("dif1");
    model.geom("part8").create("uni1", "Union");
    model.geom("part8").feature("uni1").selection("input").set("dif1");
    model.geom("part8").feature("uni1").set("intbnd", false);
    model.geom("part8").run("uni1");
    model.geom("part8").create("pi1", "PartInstance");
    model.geom("part8").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part8").feature("pi1").set("part", "part3");
    model.geom("part8").run("pi1");
    model.geom("part8").selection().create("csel1", "CumulativeSelection");
    model.geom("part8").selection("csel1").label("Inner Fluid Domain");
    model.geom("part8").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.geom("part8").selection().create("csel2", "CumulativeSelection");
    model.geom("part8").selection("csel2").label("Inner Fluid Inlet");
    model.geom("part8").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel2");
    model.geom("part8").selection().create("csel3", "CumulativeSelection");
    model.geom("part8").selection("csel3").label("Inner Fluid Outlet");
    model.geom("part8").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel3");
    model.geom("part8").selection().create("csel4", "CumulativeSelection");
    model.geom("part8").selection("csel4").label("Flow Periodicity");
    model.geom("part8").feature("pi1").setEntry("selcontributetobnd", "pi1_csel4.bnd", "csel4");
    model.geom("part8").create("co1", "Compose");
    model.geom("part8").feature("co1").set("formula", "uni1-pi1+pi1");
    model.geom("part8").run("co1");
    model.geom("part8").create("sel1", "ExplicitSelection");
    model.geom("part8").feature("sel1").label("Selection: Temperature Periodicity");
    model.geom("part8").feature("sel1").selection("selection").init(2);
    model.geom("part8").feature("sel1").selection("selection").set("co1", 1, 2, 52, 53);
    model.geom("part8").run("sel1");
    model.geom("part8").create("sel2", "ExplicitSelection");
    model.geom("part8").feature("sel2").label("Selection: Exterior Fins");
    model.geom("part8").feature("sel2").selection("selection").init(2);
    model.geom("part8").run("sel2");
    model.geom("part8").create("sel3", "ExplicitSelection");
    model.geom("part8").feature("sel3").label("Selection: Composite Faces");
    model.geom("part8").feature("sel3").selection("selection").init(2);
    model.geom("part8").feature("sel3").selection("selection").set("co1", 36);
    model.geom("part8").feature("sel3").set("groupcontang", true);
    model.geom("part8").selection().create("csel5", "CumulativeSelection");
    model.geom("part8").selection("csel5").label("Pressure Constraint");
    model.geom("part8").feature("pi1").setEntry("selkeeppnt", "pi1_csel5.pnt", false);
    model.geom("part8").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel5");
    model.geom("part8").run("sel3");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part5");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Rout", "Rout_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "L0", "L0_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "L_fins", "L_fins_i");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Inner Fluid Domain");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Temperature Periodicity");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel1", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Exterior Fins");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel2", "csel3");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Composite Faces");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel3", "csel4");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Inner Fluid Inlet");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel5");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("Inner Fluid Outlet");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel6");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("Flow Periodicity");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel4.bnd", "csel7");
    model.component("comp1").geom("geom1").selection().create("csel8", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel8").label("Pressure Constraint");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel8");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("Selection: Whole Pipe");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2.5*Rout_i");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L0_i");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-L0_i/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("size", new String[]{"L0_i", "20*(Rout_i+Rinn_i)", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "5*(Rout_i+Rinn_i)", 2);
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"-L0_i/2", "-20*(Rout_i+Rinn_i)/4", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-5*(Rout_i+Rinn_i)/2", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("Selection: Mesh, Left Boundary");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("blk1", 2);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("Selection: Outer Fluid Inlet");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("blk1", 3);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("Selection: Outer Fluid Outlet");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("blk1", 6);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("Selection: Mesh Control Faces");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("cyl1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").named("csel4");
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").named("sel4");
    model.component("comp1").geom("geom1").run("mcf1");
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("Fluid Slip Condition");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmin", "-L0_i");
    model.component("comp1").selection("box1").set("xmax", "L0_i");
    model.component("comp1").selection("box1").set("ymin", "9*(Rout_i+Rinn_i)");
    model.component("comp1").selection("box1").set("ymax", "10*(Rout_i+Rinn_i)");
    model.component("comp1").selection("box1").set("zmin", "-5*(Rout_i+Rinn_i)");
    model.component("comp1").selection("box1").set("zmax", "5*(Rout_i+Rinn_i)");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("Exterior Fluid Domain");
    model.component("comp1").selection("box2").set("entitydim", 3);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("Fluid Domains");
    model.component("comp1").selection("uni1").set("input", new String[]{"box2", "geom1_csel1_dom"});
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("Pipe Domain");
    model.component("comp1").selection("com1").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("All Pipe Boundaries");
    model.component("comp1").selection("adj1").set("input", new String[]{"com1"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("Periodicity");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel2_bnd", "geom1_csel7_bnd"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("Pipe Inner Walls");
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_csel1_dom"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("Pipe Walls");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_boxsel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_csel7_bnd"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("Mesh, Pipe and Inlet");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"uni2", "geom1_sel1"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("Inner Heat Exchange Walls");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"geom1_csel5_bnd", "geom1_csel6_bnd"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").label("Outer Heat Exchange Walls");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"uni2", "dif2", "geom1_csel3_bnd"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("All Pipe Walls and Fins");
    model.component("comp1").selection("uni3").set("entitydim", 2);
    model.component("comp1").selection("uni3").set("input", new String[]{"adj1", "geom1_csel3_bnd"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("Average: Inner Fluid Inlet");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("geom1_csel5_bnd");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("Average: Inner Fluid Outlet");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("geom1_csel6_bnd");
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").label("Average: Outer Fluid Inlet");
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop3").selection().named("geom1_sel2");
    model.component("comp1").cpl().create("aveop4", "Average");
    model.component("comp1").cpl("aveop4").set("axisym", true);
    model.component("comp1").cpl("aveop4").label("Average: Outer Fluid Outlet");
    model.component("comp1").cpl("aveop4").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop4").selection().named("geom1_sel3");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("Integration: Inner Heat Exchange Walls");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("dif2");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("Integration: Inner Fluid Inlet");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_csel5_bnd");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat4").label("Copper 1");
    model.component("comp1").material("mat4").set("family", "copper");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"eta(T0)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho(pA0,T0)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k(T0)"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", new String[]{"Cp(T0)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"eta(T0)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T0)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho(T0)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"k(T0)"});
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat3").selection().named("com1");
    model.component("comp1").material("mat4").label("Copper Fins");
    model.component("comp1").material("mat4").selection().geom("geom1", 2);
    model.component("comp1").material("mat4").selection().named("geom1_csel3_bnd");
    model.component("comp1").material("mat4").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat4").propertyGroup("shell").set("lth", new String[]{"th_fins_i"});

    model.component("comp1").physics("ht").feature("fluid1").selection().named("uni1");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("init2", "init", 3);
    model.component("comp1").physics("ht").feature("init2").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "T0+dT");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("ht").feature("sls1").set("LayerType", "Conductive");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_sel3");
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 2);
    model.component("comp1").physics("ht").feature("pc1").selection().named("uni2");
    model.component("comp1").physics("ht").feature("pc1").set("DeltaT", "dT_pipe");
    model.component("comp1").physics("ht").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("ht").feature("ge1").setIndex("name", "dT_pipe", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("equation", "(T0+dT-aveop1(T))*1e6", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").set("DependentVariableQuantity", "temperature");
    model.component("comp1").physics("ht").feature("ge1").set("SourceTermQuantity", "temperature");
    model.component("comp1").physics("spf").selection().named("uni1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("TurbulenceModelProperty")
         .set("WallTreatment", "LowReynoldsNumber");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "pA0");
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"0", "u0_outer", "0"});
    model.component("comp1").physics("spf").create("init2", "init", 3);
    model.component("comp1").physics("spf").feature("init2").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("spf").feature("init2")
         .set("u_init", new String[]{"mfr0_inner/intop2(mat2.def.rho(T0[1/K])[kg/m^3])", "0", "0"});
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().named("box1");
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_sel2");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "dp_outer");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel3");
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 2);
    model.component("comp1").physics("spf").feature("pfc1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("spf").feature("pfc1").set("pdiff", "dp_inner");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().named("geom1_csel8_pnt");
    model.component("comp1").physics("spf").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge1").setIndex("name", "dp_inner", 0, 0);
    model.component("comp1").physics("spf").feature("ge1")
         .setIndex("equation", "aveop1(u)-mfr0_inner/intop2(mat2.def.rho(T0))", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("name", "dp_outer", 1, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("equation", "aveop3(v)-u0_outer", 1, 0);
    model.component("comp1").physics("spf").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("spf").feature("ge1").set("SourceTermQuantity", "velocity");

    model.component("comp1").mesh("mesh1").label("Mesh: No Fins/Grooves");
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size1").active(false);
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").active(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").active(false);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").active(false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("uni3");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 5);
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("Mesh: Fins/Grooves");
    model.component("comp1").mesh("mesh2").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh2").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("size1").selection().named("com1");
    model.component("comp1").mesh("mesh2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh2").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh2").feature("size2").selection().set(4);
    model.component("comp1").mesh("mesh2").create("size3", "Size");
    model.component("comp1").mesh("mesh2").feature("size3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("size3").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh2").feature("size3").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size3").set("hauto", 2);
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri1").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").selection().named("int1");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh2").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("swe1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh2").feature().duplicate("swe2", "swe1");
    model.component("comp1").mesh("mesh2").feature("swe2").selection().set(1);
    model.component("comp1").mesh("mesh2").create("conv1", "Convert");
    model.component("comp1").mesh("mesh2").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("conv1").selection().named("dif2");
    model.component("comp1").mesh("mesh2").create("conv2", "Convert");
    model.component("comp1").mesh("mesh2").feature("conv2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("conv2").selection().set(46, 47, 48, 49);
    model.component("comp1").mesh("mesh2").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri2").selection().named("geom1_csel3_bnd");
    model.component("comp1").mesh("mesh2").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("ftri2").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("ftet1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh2").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("bl1").selection().named("uni1");
    model.component("comp1").mesh("mesh2").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh2").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh2").feature("bl1").create("blp1", "BndLayerProp");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").selection().named("dif1");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blhminfact", 2);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").selection().named("dif2");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("blnlayers", 4);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("blhminfact", 8);
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "th_i", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "th_i", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "u0_outer", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "range(u0_outer_min,(u0_outer_max-(u0_outer_min))/(u0_outer_steps-1),u0_outer_max)", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("wdi").setEntry("mesh", "geom1", "mesh2");
    model.study("std1").feature("wdi").set("usestol", true);
    model.study("std1").feature("wdi").set("stol", "rtol");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature("stat").setEntry("mesh", "geom1", "mesh2");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "rtol");
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").set("usestol", true);
    model.study("std1").feature("stat2").set("stol", "rtol");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("dDef").active(true);
    model.sol("sol1").feature("s1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("fc1").set("damp", "0.5");
    model.sol("sol1").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s2").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 100);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset4");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(36);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Temperature (ht)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().set(1, 2, 3);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").label("Domain");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").feature().create("vol2", "Volume");
    model.result("pg1").feature("vol2").label("Layered Shell");
    model.result("pg1").feature("vol2").set("data", "lshl1");
    model.result("pg1").feature("vol2").setIndex("looplevel", 2, 0);
    model.result("pg1").feature("vol2").set("solutionparams", "parent");
    model.result("pg1").feature("vol2").set("titletype", "none");
    model.result("pg1").feature("vol2").set("smooth", "internal");
    model.result("pg1").feature("vol2").set("data", "lshl1");
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("Layered Shell Edges");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result("pg1").feature("line1").setIndex("looplevel", 2, 0);
    model.result("pg1").feature("line1").set("solutionparams", "parent");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result().dataset("dset4").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Velocity (spf)");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("Slice");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("Exterior Walls");
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("Interior Walls");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset("surf1").set("data", "dset4");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 3, 4, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41);
    model.result().dataset("surf2").set("data", "dset4");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection().set(36);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Pressure (spf)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg3").feature().create("slit1", "SurfaceSlit");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").setIndex("looplevel", 2, 0);

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg3").feature("slit1").set("upexpr", "up(p)");
    model.result("pg3").feature("slit1").set("downexpr", "down(p)");
    model.result("pg3").feature("slit1").set("titletype", "none");
    model.result("pg3").feature("slit1").set("smooth", "internal");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Wall Resolution (spf)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Wall Resolution");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature().create("slit1", "SurfaceSlit");
    model.result("pg4").feature("slit1").label("Wall Resolution, Interior Walls");
    model.result("pg4").feature("slit1").set("data", "surf2");
    model.result("pg4").feature("slit1").setIndex("looplevel", 2, 0);
    model.result("pg4").feature("slit1").set("upexpr", "spf.Delta_wPlus_u");
    model.result("pg4").feature("slit1").set("downexpr", "spf.Delta_wPlus_d");
    model.result("pg4").feature("slit1").set("smooth", "internal");
    model.result("pg4").feature("slit1").set("data", "surf2");
    model.result("pg4").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Temperature and Fluid Flow (nitf1)");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("Wall Temperature");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 3, 4, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("Solid Temperature");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "nitf1.T");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg5").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg5").feature("vol1").feature("sel1").selection().set(2);
    model.result("pg5").feature("vol1").set("inheritplot", "surf1");
    model.result("pg5").feature().create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").label("Fluid Flow");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("solutionparams", "parent");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg5").feature("arwv1").set("xnumber", 30);
    model.result("pg5").feature("arwv1").set("ynumber", 30);
    model.result("pg5").feature("arwv1").set("znumber", 30);
    model.result("pg5").feature("arwv1").set("arrowtype", "cone");
    model.result("pg5").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("data", "parent");
    model.result("pg5").feature("arwv1").feature().create("col1", "Color");
    model.result("pg5").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg5").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("All Pipe Walls with Fins");
    model.result().dataset("surf3").set("data", "dset4");
    model.result().dataset("surf3").selection().named("uni3");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("Pipe Temperature, Fluid Velocity");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "Pipe Temperature, Fluid Velocity");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("legendpos", "alternating");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "surf3");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("expr", "T+dT_pipe");
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature("surf2").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").feature("trn1").set("move", new String[]{"-L0_i", "0", "0"});
    model.result("pg6").feature("surf2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").set("expr", "T+2*dT_pipe");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").feature("trn1").set("move", new String[]{"-2*L0_i", "0", "0"});
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf4", "surf2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf3").set("expr", "T-dT_pipe");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").feature("trn1").set("move", new String[]{"L0_i", "0", "0"});
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf5", "surf3");
    model.result("pg6").run();
    model.result("pg6").feature("surf5").set("expr", "T-2*dT_pipe");
    model.result("pg6").run();
    model.result("pg6").feature("surf5").feature("trn1").set("move", new String[]{"2*L0_i", "0", "0"});
    model.result("pg6").run();
    model.result("pg6").create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("expr", "spf.U");
    model.result("pg6").feature("mslc1").set("descr", "Velocity magnitude");
    model.result("pg6").feature("mslc1").set("xnumber", "2");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", 0);
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", 0);
    model.result("pg6").feature().duplicate("mslc2", "mslc1");
    model.result("pg6").run();
    model.result("pg6").feature("mslc2").set("xnumber", "0");
    model.result("pg6").feature("mslc2").set("inheritplot", "mslc1");
    model.result("pg6").feature("mslc2").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("mslc2").feature("trn1").set("move", new String[]{"-L0_i", "0", "0"});
    model.result("pg6").feature("mslc2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("mslc3", "mslc2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("mslc3").feature("trn1").set("move", new String[]{"L0_i", "0", "0"});
    model.result("pg6").run();
    model.result("pg6").create("arwv1", "ArrowVolume");
    model.result("pg6").feature("arwv1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arwv1").set("descr", "Velocity field");
    model.result("pg6").feature("arwv1").set("xnumber", 1);
    model.result("pg6").feature("arwv1").set("ynumber", 11);
    model.result("pg6").feature("arwv1").set("arrowtype", "cone");
    model.result("pg6").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arwv1").set("color", "custom");
    model.result("pg6").feature("arwv1")
         .set("customcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg6").feature("arwv1").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("arwv1").feature("trn1").set("move", new String[]{"-L0_i", "0", "0"});
    model.result("pg6").feature("arwv1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("arwv2", "arwv1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("arwv2").feature("trn1").set("move", new String[]{"L0_i", "0", "0"});
    model.result("pg6").run();
    model.result("pg6").create("arwv3", "ArrowVolume");
    model.result("pg6").feature("arwv3").setIndex("expr", "u", 0);
    model.result("pg6").feature("arwv3").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arwv3").set("xnumber", 1);
    model.result("pg6").feature("arwv3").set("ynumber", 4);
    model.result("pg6").feature("arwv3").set("znumber", 4);
    model.result("pg6").feature("arwv3").set("arrowtype", "cone");
    model.result("pg6").feature("arwv3").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arwv3").set("color", "custom");
    model.result("pg6").feature("arwv3")
         .set("customcolor", new double[]{0.6980392336845398, 0.13333334028720856, 0.13333334028720856});
    model.result("pg6").feature("arwv3").create("sel1", "Selection");
    model.result("pg6").feature("arwv3").feature("sel1").selection().named("geom1_csel1_dom");
    model.result("pg6").run();
    model.result("pg6").feature("arwv3").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("arwv3").feature("trn1").set("move", new String[]{"-3*L0_i", "0", "0"});
    model.result("pg6").feature("arwv3").feature("trn1").set("applytodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("arwv4", "arwv3");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("arwv4").feature("trn1").set("move", new String[]{"3*L0_i", "0", "0"});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("Heat Dissipation Rate");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "Heat Dissipation Rate");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1")
         .setIndex("expr", "abs(intop1(ht.ntflux))/(L0_i*abs(aveop1(T)-aveop3(T)))", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "W/(m*K)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "Heat dissipation rate", 0);
    model.result("pg7").feature("glob1").set("linewidth", 3);
    model.result("pg7").feature("glob1").set("linemarker", "star");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Heat Dissipation Rate");
    model.result().numerical("gev1").set("data", "dset4");
    model.result().numerical("gev1").setIndex("expr", "abs(intop1(ht.ntflux))/(L0_i*abs(aveop1(T)-aveop3(T)))", 0);
    model.result().numerical("gev1").setIndex("descr", "Heat dissipation rate", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Heat Dissipation Rate");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Pressure Drop, Outer Fluid");
    model.result().numerical("gev2").set("data", "dset4");
    model.result().numerical("gev2").setIndex("expr", "aveop3(p)-aveop4(p)", 0);
    model.result().numerical("gev2").setIndex("descr", "Pressure drop, outer fluid", 0);
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Pressure Drop, Inner Fluid");
    model.result().numerical("gev3").set("data", "dset4");
    model.result().numerical("gev3").setIndex("expr", "(aveop1(p)-aveop2(p))*1[m]/L0_i", 0);
    model.result().numerical("gev3").setIndex("descr", "Pressure drop, inner fluid", 0);
    model.result().numerical("gev3").set("table", "tbl1");
    model.result().numerical("gev3").appendResult();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Temperature Drop, Inner Fluid");
    model.result().numerical("gev4").set("data", "dset4");
    model.result().numerical("gev4").setIndex("expr", "dT_pipe*1[m]/L0_i", 0);
    model.result().numerical("gev4").setIndex("descr", "Temperature drop, inner fluid", 0);
    model.result().numerical("gev4").set("table", "tbl1");
    model.result().numerical("gev4").appendResult();
    model.result("pg6").run();

    model.title("\u7fc5\u7247\u7ba1");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u5305\u542b\u8f93\u51fa\u7684\u201c\u7ed3\u679c\u8868\u201d\u8868\u5355\u5bf9\u8c61\n\n\u7fc5\u7247\u7ba1\u7528\u4e8e\u5728\u51b7\u5374\u5668\u3001\u52a0\u70ed\u5668\u6216\u6362\u70ed\u5668\u4e2d\u63d0\u9ad8\u4f20\u70ed\u6548\u7387\u3002\u5b83\u4eec\u6839\u636e\u4e0d\u540c\u7684\u5e94\u7528\u548c\u9700\u6c42\u5177\u6709\u4e0d\u540c\u7684\u5c3a\u5bf8\u548c\u8bbe\u8ba1\u3002\n\n\u5f53\u7fc5\u7247\u653e\u7f6e\u5728\u7ba1\u9053\u5916\u90e8\u65f6\uff0c\u53ef\u4ee5\u589e\u52a0\u7ba1\u9053\u7684\u6362\u70ed\u8868\u9762\u79ef\uff0c\u4f7f\u51b7\u5374\u6216\u52a0\u70ed\u7684\u5916\u90e8\u6d41\u4f53\u80fd\u591f\u66f4\u6709\u6548\u5730\u8fdb\u884c\u70ed\u4ea4\u6362\uff1b\u800c\u653e\u7f6e\u5728\u7ba1\u9053\u5185\u90e8\u65f6\uff0c\u589e\u52a0\u7684\u6362\u70ed\u8868\u9762\u79ef\u53ef\u4ee5\u63d0\u5347\u5185\u90e8\u6d41\u4f53\u7684\u6548\u80fd\u3002\u9664\u7fc5\u7247\u5916\uff0c\u51f9\u69fd\u4e5f\u53ef\u4ee5\u589e\u52a0\u6362\u70ed\u8868\u9762\u79ef\uff0c\u5728\u7a7a\u95f4\u6709\u9650\u7684\u7ba1\u9053\u5185\u90e8\u5c24\u5176\u5982\u6b64\u3002\n\n\u901a\u8fc7\u4f7f\u7528\u8be5 App\uff0c\u60a8\u53ef\u4ee5\u5b9a\u5236\u4e00\u6839\u957f\u5706\u67f1\u7ba1\uff0c\u5e76\u5bf9\u5176\u4f7f\u7528\u9884\u5b9a\u4e49\u7684\u5185\u5916\u7fc5\u7247\u6216\u51f9\u69fd\uff0c\u4ece\u800c\u89c2\u5bdf\u548c\u8bc4\u4f30\u5176\u51b7\u5374\u6548\u679c\u3002\u8be5 App \u53ef\u4ee5\u9488\u5bf9\u4ee5\u4e0b\u7ba1\u9053\u60c5\u51b5\u8ba1\u7b97\u5176\u70ed\u6027\u80fd\uff1a\u7ba1\u9053\u6700\u521d\u5145\u6ee1\u6c34\uff0c\u7136\u540e\u901a\u8fc7\u5468\u56f4\u7a7a\u6c14\u7684\u5f3a\u5236\u5bf9\u6d41\u5b9e\u73b0\u51b7\u5374\u6216\u52a0\u70ed\u3002\n\n\u5916\u90e8\u7ed3\u6784\uff08\u5706\u76d8\u5806\u53e0\u5f0f\u53f6\u7247\u3001\u5706\u69fd\u3001\u87ba\u65cb\u53f6\u7247\u3001\u87ba\u65cb\u69fd\u6216\u201c\u65e0\u201d\uff09\u548c\u5185\u90e8\u7ed3\u6784\uff08\u76f4\u69fd\u6216\u201c\u65e0\u201d\uff09\u53ef\u4ee5\u91c7\u7528\u5404\u79cd\u4e0d\u540c\u7684\u51e0\u4f55\u6784\u578b\u3002\n\n\u8be5 App \u5c06\u8017\u6563\u529f\u7387\u548c\u538b\u964d\u4f5c\u4e3a\u51e0\u4f55\u548c\u7a7a\u6c14\u6d41\u901f\u7684\u51fd\u6570\u8fdb\u884c\u8ba1\u7b97\u3002");

    model.label("finned_pipe.mph");

    model.result("pg6").run();

    model.param().set("rho_pipe", "8960[kg/m^3]");
    model.param().descr("rho_pipe", "\u7ba1\u6750\u5bc6\u5ea6");
    model.param()
         .set("vol_pipe", "(pi*(Rout_i^2-Rinn_i^2)*L0_i+pi*((Rout_i+L_fins_i)^2-Rout_i^2)*th_fins_i)*1[m]/L0_i");
    model.param().descr("vol_pipe", "\u7ba1\u5bb9\u79ef");
    model.param().set("mass_pipe", "rho_pipe*vol_pipe");
    model.param().descr("mass_pipe", "\u7ba1\u8d28\u91cf");
    model.param().set("vol_innfluid", "(pi*Rinn_i^2)*1[m]");
    model.param().descr("vol_innfluid", "\u5185\u90e8\u6d41\u4f53\u4f53\u79ef");
    model.param().set("area_innerexch", "2*pi*Rinn_i*1[m]");
    model.param().descr("area_innerexch", "\u5185\u90e8\u70ed\u4ea4\u6362\u8868\u9762\u79ef");
    model.param()
         .set("area_outerexch", "(2*pi*Rout_i*(L0_i-th_fins_i)+2*pi*((Rout_i+L_fins_i)^2-Rout_i^2)+2*pi*(Rout_i+L_fins_i)*th_fins_i)*1[m]/L0_i");
    model.param().descr("area_outerexch", "\u5916\u90e8\u70ed\u4ea4\u6362\u8868\u9762\u79ef");
    model.param().set("T0_water", "T0[1/K]+10");
    model.param().descr("T0_water", "\u8fdb\u6c34\u53e3\u6e29\u5ea6");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh3");
    model.component("comp2").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part5");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "Rout", "Rout_i");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "L0", "L0_i");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "L_fins", "L_fins_i");
    model.component("comp2").geom("geom2").run("pi1");
    model.component("comp2").geom("geom2").selection().create("csel1", "CumulativeSelection");
    model.component("comp2").geom("geom2").selection("csel1").label("\u5185\u90e8\u6d41\u4f53\u57df");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp2").geom("geom2").create("arr1", "Array");
    model.component("comp2").geom("geom2").feature("arr1").selection("input").set("pi1");
    model.component("comp2").geom("geom2").feature("arr1").set("type", "linear");
    model.component("comp2").geom("geom2").feature("arr1").set("linearsize", 5);
    model.component("comp2").geom("geom2").feature("arr1").set("displ", new String[]{"L0_i", "0", "0"});
    model.component("comp2").geom("geom2").run("arr1");

    model.component("comp2").view("view14").hideObjects().create("hide1");
    model.component("comp2").view("view14").hideObjects("hide1").init(3);
    model.component("comp2").view("view14").hideObjects("hide1").named("csel1");

    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").mesh().remove("mesh3");

    model.sol("sol1").updateSolution();
    model.sol("sol4").updateSolution();

    model.result().report().create("rpt1", "Report");

    model.setExpectedComputationTime("10 \u5206\u949f");

    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///finned_pipe.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("logo", "none");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature("toc1").set("levels", "1");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec1").feature("comp1").label("\u8f6f\u4ef6\u5c5e\u6027");
    model.result().report("rpt1").feature("sec1").feature("comp1").set("includeauthor", true);
    model.result().report("rpt1").feature("sec1").feature("comp1").set("includeversion", true);
    model.result().report("rpt1").feature("sec1").feature("comp1").set("commentssource", "none");
    model.result().report("rpt1").feature("sec1").feature("comp1").set("noderef", "none");
    model.result().report("rpt1").feature("sec1").feature("comp1").set("includeunitsystem", false);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1")
         .set("children", new String[][]{{"param", "off"}, {"wdi", "off"}, {"stat", "off"}, {"stat2", "off"}});
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ec4\u4ef6");
    model.result().report("rpt1").feature("sec3").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec3").feature("geom1").set("noderef", "geom2");
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec3").feature("geom1")
         .set("children", new String[][]{{"pi1", "off"}, {"arr1", "off"}, {"fin", "off"}});
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh1")
         .set("children", new String[][]{{"size", "off"}, 
         {"size1", "off"}, 
         {"size2", "off"}, 
         {"ftri1", "off"}, 
         {"swe1", "off"}, 
         {"bl1", "off"}});
    model.result().report("rpt1").feature("sec3").feature().create("mesh2", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh2").set("noderef", "mesh2");
    model.result().report("rpt1").feature("sec3").feature("mesh2").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh2")
         .set("children", new String[][]{{"size", "off"}, 
         {"size1", "off"}, 
         {"size2", "off"}, 
         {"size3", "off"}, 
         {"ftri1", "off"}, 
         {"swe1", "off"}, 
         {"swe2", "off"}, 
         {"conv1", "off"}, 
         {"conv2", "off"}, 
         {"ftri2", "off"}, 
         {"ftet1", "off"}, 
         {"bl1", "off"}});
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec4").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec4").feature("param1").set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("\u6563\u70ed\u7387");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("noderef", "pg7");
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2")
         .label("\u7ba1\u6e29\u5ea6\uff0c\u6d41\u4f53\u901f\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg6");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").set("numberformat", "custom");
    model.result("pg6").run();

    model.title("\u7fc5\u7247\u7ba1");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u5305\u542b\u8f93\u51fa\u7684\u201c\u7ed3\u679c\u8868\u201d\u8868\u5355\u5bf9\u8c61\n\n\u7fc5\u7247\u7ba1\u7528\u4e8e\u5728\u51b7\u5374\u5668\u3001\u52a0\u70ed\u5668\u6216\u6362\u70ed\u5668\u4e2d\u63d0\u9ad8\u4f20\u70ed\u6548\u7387\u3002\u5b83\u4eec\u6839\u636e\u4e0d\u540c\u7684\u5e94\u7528\u548c\u9700\u6c42\u5177\u6709\u4e0d\u540c\u7684\u5c3a\u5bf8\u548c\u8bbe\u8ba1\u3002\n\n\u5f53\u7fc5\u7247\u653e\u7f6e\u5728\u7ba1\u9053\u5916\u90e8\u65f6\uff0c\u53ef\u4ee5\u589e\u52a0\u7ba1\u9053\u7684\u6362\u70ed\u8868\u9762\u79ef\uff0c\u4f7f\u51b7\u5374\u6216\u52a0\u70ed\u7684\u5916\u90e8\u6d41\u4f53\u80fd\u591f\u66f4\u6709\u6548\u5730\u8fdb\u884c\u70ed\u4ea4\u6362\uff1b\u800c\u653e\u7f6e\u5728\u7ba1\u9053\u5185\u90e8\u65f6\uff0c\u589e\u52a0\u7684\u6362\u70ed\u8868\u9762\u79ef\u53ef\u4ee5\u63d0\u5347\u5185\u90e8\u6d41\u4f53\u7684\u6548\u80fd\u3002\u9664\u7fc5\u7247\u5916\uff0c\u51f9\u69fd\u4e5f\u53ef\u4ee5\u589e\u52a0\u6362\u70ed\u8868\u9762\u79ef\uff0c\u5728\u7a7a\u95f4\u6709\u9650\u7684\u7ba1\u9053\u5185\u90e8\u5c24\u5176\u5982\u6b64\u3002\n\n\u901a\u8fc7\u4f7f\u7528\u8be5 App\uff0c\u60a8\u53ef\u4ee5\u5b9a\u5236\u4e00\u6839\u957f\u5706\u67f1\u7ba1\uff0c\u5e76\u5bf9\u5176\u4f7f\u7528\u9884\u5b9a\u4e49\u7684\u5185\u5916\u7fc5\u7247\u6216\u51f9\u69fd\uff0c\u4ece\u800c\u89c2\u5bdf\u548c\u8bc4\u4f30\u5176\u51b7\u5374\u6548\u679c\u3002\u8be5 App \u53ef\u4ee5\u9488\u5bf9\u4ee5\u4e0b\u7ba1\u9053\u60c5\u51b5\u8ba1\u7b97\u5176\u70ed\u6027\u80fd\uff1a\u7ba1\u9053\u6700\u521d\u5145\u6ee1\u6c34\uff0c\u7136\u540e\u901a\u8fc7\u5468\u56f4\u7a7a\u6c14\u7684\u5f3a\u5236\u5bf9\u6d41\u5b9e\u73b0\u51b7\u5374\u6216\u52a0\u70ed\u3002\n\n\u5916\u90e8\u7ed3\u6784\uff08\u5706\u76d8\u5806\u53e0\u5f0f\u53f6\u7247\u3001\u5706\u69fd\u3001\u87ba\u65cb\u53f6\u7247\u3001\u87ba\u65cb\u69fd\u6216\u201c\u65e0\u201d\uff09\u548c\u5185\u90e8\u7ed3\u6784\uff08\u76f4\u69fd\u6216\u201c\u65e0\u201d\uff09\u53ef\u4ee5\u91c7\u7528\u5404\u79cd\u4e0d\u540c\u7684\u51e0\u4f55\u6784\u578b\u3002\n\n\u8be5 App \u5c06\u8017\u6563\u529f\u7387\u548c\u538b\u964d\u4f5c\u4e3a\u51e0\u4f55\u548c\u7a7a\u6c14\u6d41\u901f\u7684\u51fd\u6570\u8fdb\u884c\u8ba1\u7b97\u3002");

    return model;
  }

  public static Model run5(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("finned_pipe.mph");

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
