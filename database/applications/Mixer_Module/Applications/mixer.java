/*
 * mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:13 by COMSOL 6.3.0.290. */
public class mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a_bs_ib_i", "0");
    model.param().set("ab_ib_i", "30[deg]", "Bending angle of folded section");
    model.param().set("ac_h_s_ib_i", "30", "Shear angle, back of c-shape blades");
    model.param().set("ac_ib_i", "30", "Vertical angle between arm and c-shaped blade");
    model.param().set("ac_s_ib_i", "30");
    model.param().set("ac_s_ib_ib", "30[deg]", "Internal angle, c-shaped profile");
    model.param().set("ap_ib_i", "45", "Pitch angle of impeller blade");
    model.param().set("ar_ib_i", "0", "Rake angle of impeller");
    model.param().set("as_ib_i", "240[deg]", "Relative rotation angle around shaft");
    model.param()
         .set("bo_rd_ta_i", "(1/10)[1]", "Bottom offset of rotating domain relative to the tank bottom minor radius");
    model.param().set("d_a_ib_i", "0.12[m]", "Diameter of attachment section");
    model.param().set("d_cs_im_i", "1.05*d_im_i", "Control surface diameter");
    model.param().set("d_hu_i", "0.1", "Impeller hub diameter");
    model.param().set("d_id_i", "0.45[m]", "Diameter of impeller dish");
    model.param().set("d_im_i", "0.7[m]", "Impeller diameter");
    model.param().set("d_is_i", "0.05[m]", "Impeller shaft diameter");
    model.param().set("d_ta_i", "1.9[m]", "Tank diameter");
    model.param().set("Da", "d_im_i", "Input diameter for turbulence model");
    model.param().set("db_im_i", "0.3[m]", "Distance of first impeller hub from bottom");
    model.param().set("dc_ib_i", "0.08", "Blade width, c-shaped blades");
    model.param().set("density", "1e3", "Fluid density");
    model.param().set("h_an_im_i", "0.6[m]", "Height of anchor impeller");
    model.param().set("H_mesh", "0.05[m]", "Mesh parameter");
    model.param().set("h_ta_i", "2.5[m]", "Tank height");
    model.param().set("hc_ta_i", "0.3[m]", "Height of cone section");
    model.param().set("hp_im_i", "1.85[m]", "Impeller position");
    model.param().set("hp_is_i", "0[m]", "Position of the lowest part of the impeller shaft");
    model.param().set("hp_ta_i", "0[m]", "Tank position along the z-axis");
    model.param().set("l_ib_i", "0.15[m]", "Length of the impeller blades, Rushton, Smith");
    model.param().set("l_is_i", "2.2[m]", "Impeller shaft length");
    model.param().set("lc_ib_i", "0.08", "Blade length, c-shaped blades");
    model.param()
         .set("lr_bu_ib_i", "0.3[1]", "Relative position of the bending point along the length of the upper edge of the impeller blade");
    model.param()
         .set("lr_cl_ib_i", "0.5[1]", "Relative position of cutting point along the length of the lower edge");
    model.param()
         .set("lr_cu_ib_i", "0.5[1]", "Relative position of cutting point along the length of the upper edge");
    model.param().set("miu", "viscosity", "Input viscosity");
    model.param().set("n_ba_i", "0", "Number of baffles");
    model.param().set("n_ib_i", "3[1]", "Number of blades");
    model.param().set("off", "0[1]");
    model.param().set("Omega", "(rpm*2*pi[rad])", "Angular velocity");
    model.param().set("on", "1[1]");
    model.param()
         .set("pa_cs_im_i", "1", "Parameter that adds control surfaces around the impellers (= 1 on, = 0 off)");
    model.param()
         .set("pa_rd_im_i", "1", "Parameter that controls the impeller blade direction, 1 = clockwise and -1 = counterclockwise");
    model.param().set("pa_ud_im_i", "off", "Flip impeller vertically");
    model.param().set("rc_h_ib_i", "0.3[m]", "Curvature of backswept Rushton, Smith, and Backswept impeller");
    model.param().set("rc_v_ib_i", "0.15[m]", "Hydrofoil curvature radius closest to the hub");
    model.param().set("rf_ib_i", "0.02[m]", "Fillet radius of outer vertices");
    model.param().set("rf_ta_i", "0.1[m]", "Fillet radius for lower tank edge for flat bottomed tank");
    model.param().set("rho", "density", "Input density");
    model.param().set("rm_b_ta_i", "0.3[m]", "Radius of dished section for dished bottom tank");
    model.param().set("rmi_an_im_i", "0.25[m]", "Minor radius of anchor impeller");
    model.param().set("rpm", "120[1/min]", "Revolutions per minute");
    model.param().set("sigma", "0.072[N/m]", "Surface tension of the fluid");
    model.param().set("theta", "0*(pi/2)[rad]");
    model.param().set("TIME", "0[s]");
    model.param().set("viscosity", "1e-3", "Fluid viscosity");
    model.param().set("w_a_ib_i", "0.05[m]", "Blade attachment width");
    model.param().set("w_ba_i", "0.2", "Baffle width");
    model.param().set("w_bou_ib_i", "0.075[m]", "Width of the folded section at the outer edge");
    model.param().set("w_cil_ib_i", "0[m]", "Width of inner and lower cut");
    model.param().set("w_ciu_ib_i", "0.025[m]", "Width of inner and upper cut");
    model.param().set("w_col_ib_i", "0.075[m]", "Width of outer and lower cut");
    model.param().set("w_cou_ib_i", "0[m]", "Width of outer and upper cut");
    model.param().set("w_ib_i", "0.08", "Impeller blade width");
    model.param().set("w_o_ib_i", "0.05", "Outer impeller width");
    model.param().set("wc_ib_i", "0.08", "Blade distance, c-shaped blades");
    model.param().set("wr_ai_ib_i", "0.5[1]", "Ratio between blade width and blade attachment");
    model.param()
         .set("wr_asu_ib_i", "1", "Asymmetry factor for upper part of the width of the impeller blade: 1 = symmetry, >1 = wider upper part");
    model.param().set("wr_io_ib_i", "0.6");

    model.geom().load(new String[]{"part1"}, "Mixer_Module\\Tanks\\dished_bottom_tank.mph", new String[]{"part1"});
    model.geom().load(new String[]{"part2"}, "Mixer_Module\\Tanks\\flat_bottom_tank.mph", new String[]{"part1"});
    model.geom().load(new String[]{"part3"}, "Mixer_Module\\Tanks\\cone_bottom_tank.mph", new String[]{"part1"});
    model.geom().load(new String[]{"part4"}, "Mixer_Module\\Shafts\\impeller_shaft.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part5"}, "Mixer_Module\\Impellers,_Axial\\pitched_blade_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part6"}, "Mixer_Module\\Impellers,_Axial\\pitched_blade_impeller_constant_pitch.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part7"}, "Mixer_Module\\Impellers,_Axial\\pitched_blade_impeller_bent_blade.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part8"}, "Mixer_Module\\Impellers,_Axial\\hydrofoil_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part9"}, "Mixer_Module\\Impellers,_Axial\\hydrofoil_impeller_constant_pitch.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part10"}, "Mixer_Module\\Impellers,_Radial\\rushton_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part11"}, "Mixer_Module\\Impellers,_Radial\\smith_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part12"}, "Mixer_Module\\Impellers,_Radial\\backswept_rushton_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part13"}, "Mixer_Module\\Impellers,_Radial\\backswept_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part14"}, "Mixer_Module\\Impellers,_Highly_Viscous_Fluids\\c_shaped_outer_blade_impeller.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part15"}, "Mixer_Module\\Impellers,_Highly_Viscous_Fluids\\anchor_impeller.mph", new String[]{"part1"});

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("tau_riw", "x*(spf.T_trac_uy+spf.T_trac_dy)-y*(spf.T_trac_ux+spf.T_trac_dx)");
    model.component("comp1").variable("var1").descr("tau_riw", "Torque per area (interior walls)");
    model.component("comp1").variable("var1").set("tau_rw", "x*(spf.T_tracy)-y*(spf.T_tracx)");
    model.component("comp1").variable("var1").descr("tau_rw", "Torque per area (rotating walls)");
    model.component("comp1").variable("var1").set("P_riw", "tau_riw*rot1.alphat");
    model.component("comp1").variable("var1").descr("P_riw", "Power draw per area (rotating interior walls)");
    model.component("comp1").variable("var1").set("P_rw", "tau_rw*rot1.alphat");
    model.component("comp1").variable("var1").descr("P_rw", "Power draw per area (rotating walls)");
    model.component("comp1").variable("var1").set("mueff", "spf.muT+spf.mu");
    model.component("comp1").variable("var1").descr("mueff", "Effective dynamic viscosity");
    model.component("comp1").variable("var1").set("Fr", "(u*x+v*y)/sqrt(x^2+y^2)*2*pi[rad]/Omega/Da^3");
    model.component("comp1").variable("var1").descr("Fr", "Radial flow number per unit area");
    model.component("comp1").variable("var1").set("Fa", "w*2*pi[rad]/Omega/Da^3");
    model.component("comp1").variable("var1").descr("Fa", "Axial flow number per unit area");

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_ba", "n_ba_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_ba", "w_ba_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_im", "d_im_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_ta", "d_ta_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "h_ta", "h_ta_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hp_ta", "hp_ta_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rm_b_ta", "rm_b_ta_i");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "bo_rd_ta", "bo_rd_ta_i");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Tank");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel7", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel7", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel1", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel1", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Rotating Domain");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel6", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel6", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel7.dom", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel7.dom", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel1.dom", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel4.dom", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel4.dom", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Surfaces to Hide");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel5", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel5", "csel3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_unisel2", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_unisel2", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel7.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel7.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel1.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel2.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel3.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "none");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Baffles");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Fluid Surface");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("Tank Walls");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel5", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel5", "csel3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_unisel2", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_unisel2", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel7.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel7.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel1.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel2.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel3.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "csel6");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("Pressure Point");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeeppnt", "pi1_csel7.pnt", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetopnt", "pi1_csel7.pnt", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeeppnt", "pi1_csel1.pnt", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetopnt", "pi1_csel1.pnt", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeeppnt", "pi1_csel5.pnt", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetopnt", "pi1_csel5.pnt", "csel7");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part4");
    model.component("comp1").geom("geom1").selection().create("csel8", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel8").label("Impeller");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepobj", "pi2_csel1", false);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoobj", "pi2_csel1", "csel8");
    model.component("comp1").geom("geom1").selection().create("csel9", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel9").label("Impeller Shaft & Hub");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_cylsel1", false);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_cylsel1", "none");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_csel1.bnd", false);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_csel1.bnd", "csel9");
    model.component("comp1").geom("geom1").selection().create("csel10", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel10").label("Edges to Remove");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepedg", "pi2_sel1", false);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoedg", "pi2_sel1", "csel10");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepedg", "pi2_csel1.edg", false);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoedg", "pi2_csel1.edg", "none");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hp_im", "hp_is_i");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_is", "d_is_i");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_is", "l_is_i");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part14");
    model.component("comp1").geom("geom1").feature("pi3").label("C-shaped double blade 1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_im", "0.7[m]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "pa_cs_im", 1);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_cs_im", 0.735);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepobj", "pi3_csel7", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetoobj", "pi3_csel7", "csel8");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepobj", "pi3_csel10", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetoobj", "pi3_csel10", "csel1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepdom", "pi3_csel10.dom", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_csel10.dom", "csel2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel14.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel14.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel11.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel11.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel12.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel12.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel13.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel13.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel5.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel5.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel6.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel6.bnd", "none");
    model.component("comp1").geom("geom1").selection().create("csel11", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel11").label("Impeller Blades");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel6.bnd", "csel11");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel14.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel14.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel11.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel11.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel12.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel12.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel13.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel13.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel5.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel5.bnd", "csel11");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_csel6.bnd", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel6.bnd", "csel9");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepedg", "pi3_sel1", false);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetoedg", "pi3_sel1", "csel10");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").feature().duplicate("pi4", "pi3");
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoobj", new String[]{"csel8", "csel1"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepobj", new String[]{"off", "off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoedg", new String[]{"csel10"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selcontributetobnd", new String[]{"csel3", "none", "none", "none", "csel11", "csel9"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selkeepbnd", new String[]{"off", "on", "on", "on", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetodom", new String[]{"csel2"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").label("C-shaped double blade 2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "as_ib", "120[deg]");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "hp_im", "0.9[m]");
    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi4");
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoobj", new String[]{"csel8", "csel1"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepobj", new String[]{"off", "off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoedg", new String[]{"csel10"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selcontributetobnd", new String[]{"csel3", "none", "none", "none", "csel11", "csel9"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selkeepbnd", new String[]{"off", "on", "on", "on", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetodom", new String[]{"csel2"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").label("C-shaped double blade 3");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "as_ib", "240[deg]");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "hp_im", "1.85[m]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel8");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").named("csel10");
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").named("csel3");

    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("Surfaces to Show");
    model.component("comp1").selection("com1").set("entitydim", 2);
    model.component("comp1").selection("com1").set("input", new String[]{"geom1_csel3_bnd"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop1").selection().named("geom1_csel7_pnt");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel5_bnd");
    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().named("geom1_csel5_bnd");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"x", "y", "h_ta_i+aveop1(z)"});
    model.component("comp1").cpl("genext1").set("usesrcmap", true);
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("maxop1").selection().named("geom1_csel11_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("Integration 1a");
    model.component("comp1").cpl("intop2").set("opname", "intop_rad_pi3");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_pi3_csel13_bnd");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").label("Integration 1b");
    model.component("comp1").cpl("intop3").set("opname", "intop_ax_pi3");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().named("geom1_pi3_csel12_bnd");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").label("Integration 2");
    model.component("comp1").cpl("intop4").set("opname", "intop_rad_pi4");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop4").selection().named("geom1_pi4_csel13_bnd");
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").label("Integration 2a");
    model.component("comp1").cpl("intop5").set("opname", "intop_ax_pi4");
    model.component("comp1").cpl("intop5").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop5").selection().named("geom1_pi4_csel12_bnd");
    model.component("comp1").cpl().create("intop6", "Integration");
    model.component("comp1").cpl("intop6").set("axisym", true);
    model.component("comp1").cpl("intop6").label("Integration 3");
    model.component("comp1").cpl("intop6").set("opname", "intop_rad_pi5");
    model.component("comp1").cpl("intop6").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop6").selection().named("geom1_pi5_csel13_bnd");
    model.component("comp1").cpl().create("intop7", "Integration");
    model.component("comp1").cpl("intop7").set("axisym", true);
    model.component("comp1").cpl("intop7").label("Integration 3a");
    model.component("comp1").cpl("intop7").set("opname", "intop_ax_pi5");
    model.component("comp1").cpl("intop7").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop7").selection().named("geom1_pi5_csel12_bnd");

    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "miu");
    model.component("comp1").physics("spf").feature("init1")
         .set("p_init", "-spf.rho*g_const*(z-(h_ta_i+aveop1(z)))");
    model.component("comp1").physics("spf").feature("init1").set("yPlus_init", 1);
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("spf").create("iwbc2", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc2").selection().named("geom1_csel11_bnd");
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 3);
    model.component("comp1").physics("spf").feature("vf1").selection().all();
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"0", "0", "-spf.rho*g_const"});
    model.component("comp1").physics("spf").create("sfs1", "StationaryFreeSurface", 2);
    model.component("comp1").physics("spf").feature("sfs1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").physics("spf").feature("sfs1").set("sigma", "sigma");
    model.component("comp1").physics("spf").feature("sfs1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("spf").create("weak1", "WeakContribution", 3);
    model.component("comp1").physics("spf").feature("weak1").selection().all();
    model.component("comp1").physics("spf").feature("weak1")
         .set("weakExpression", "-h/2*Omega*d_im_i/2*(test(ux)*(ux-nojac(ux))+test(uy)*(uy-nojac(uy))+test(uz)*(uz-nojac(uz)))");
    model.component("comp1").physics("spf").create("weak2", "WeakContribution", 3);
    model.component("comp1").physics("spf").feature("weak2").selection().all();
    model.component("comp1").physics("spf").feature("weak2")
         .set("weakExpression", "-h/2*Omega*d_im_i/2*(test(vx)*(vx-nojac(vx))+test(vy)*(vy-nojac(vy))+test(vz)*(vz-nojac(vz)))");
    model.component("comp1").physics("spf").create("weak3", "WeakContribution", 3);
    model.component("comp1").physics("spf").feature("weak3").selection().all();
    model.component("comp1").physics("spf").feature("weak3")
         .set("weakExpression", "-h/2*Omega*d_im_i/2*(test(wx)*(wx-nojac(wx))+test(wy)*(wy-nojac(wy))+test(wz)*(wz-nojac(wz)))");

    model.component("comp1").common("rot1").selection().named("geom1_csel2_dom");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "constantAngularVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "-Omega");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "d_is_i/2.1");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", "d_is_i/2.1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "H_mesh/2.1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_csel11_bnd");
    model.component("comp1").mesh("mesh1").feature().move("size2", 4);
    model.component("comp1").mesh("mesh1").feature().move("size2", 3);
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("cr1").selection().all();
    model.component("comp1").mesh("mesh1").feature("cr1").selection("boundary")
         .set(1, 2, 3, 4, 46, 47, 48, 49, 50, 51, 52, 53, 54, 57, 58, 66, 74);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").set("trimmaxangle", 20);
    model.component("comp1").mesh("mesh1").feature("bl1").set("method", "legacy60");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().named("geom1_csel6_bnd");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp2", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").selection().named("geom1_csel9_bnd");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp3", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp3").selection().named("geom1_csel11_bnd");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp3").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp3").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp4", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp4").selection().named("geom1_csel4_bnd");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp4").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp4").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("wdi").set("useinitsol", true);
    model.study("std1").feature("wdi").set("usesol", true);
    model.study("std1").feature("frrot").set("useinitsol", true);
    model.study("std1").feature("frrot").set("initmethod", "sol");
    model.study("std1").feature("frrot").set("initstudy", "std1");
    model.study("std1").feature("frrot").set("usesol", true);
    model.study("std1").feature("frrot").set("notsolmethod", "sol");
    model.study("std1").feature("frrot").set("notstudy", "std1");
    model.study("std1").create("sfs", "StationaryFreeSurface");
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("frrot").set("notsoluse", "sol2");

    model.sol("sol1").feature("s2").feature("se1").feature("ss1").set("subdamp", "1");
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subdamp", "1");
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subtermconst", "iter");
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subiter", 1);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Velocity (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("Slice");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("Exterior Walls");
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("Interior Walls");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 57, 58, 66, 74);
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 55, 56, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Pressure (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("Surface");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature().create("slit1", "SurfaceSlit");
    model.result("pg2").feature("slit1").set("data", "surf2");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("upexpr", "up(p)");
    model.result("pg2").feature("slit1").set("downexpr", "down(p)");
    model.result("pg2").feature("slit1").set("titletype", "none");
    model.result("pg2").feature("slit1").set("smooth", "internal");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("data", "surf2");
    model.result("pg2").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Wall Resolution (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Wall Resolution");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature().create("slit1", "SurfaceSlit");
    model.result("pg3").feature("slit1").label("Wall Resolution, Interior Walls");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("upexpr", "spf.Delta_wPlus_u");
    model.result("pg3").feature("slit1").set("downexpr", "spf.Delta_wPlus_d");
    model.result("pg3").feature("slit1").set("smooth", "internal");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().param().set("x_rot_post", "0[m]");
    model.result().param().set("y_rot_post", "1[m]");
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("Stationary Free Surface");
    model.result().dataset("surf3").selection().named("geom1_csel5_bnd");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("planetype", "general");
    model.result().dataset("cpl1").setIndex("genpoints", "x_rot_post", 1, 0);
    model.result().dataset("cpl1").setIndex("genpoints", "y_rot_post", 1, 1);
    model.result().dataset("cpl1").setIndex("genpoints", 0, 2, 1);
    model.result().dataset("cpl1").setIndex("genpoints", 1, 2, 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("com1");
    model.result("pg1").run();
    model.result("pg1").feature().remove("slc1");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("linetype", "ribbon");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").feature("str1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("def1")
         .set("expr", new String[]{"0", "0", "max((z-maxop1(z))/(h_ta_i+aveop1(z)-maxop1(z)),0)*(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))*exp(min(0,1-(maxop1(z)-z)/(abs(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))+sqrt(eps))))"});
    model.result("pg1").feature("str1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("str1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("data", "cpl1");
    model.result("pg1").feature("surf2").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def1")
         .set("expr", new String[]{"0", "0", "max((z-maxop1(z))/(h_ta_i+aveop1(z)-maxop1(z)),0)*(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))*exp(min(0,1-(maxop1(z)-z)/(abs(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))+sqrt(eps))))"});
    model.result("pg1").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("Eddy Diffusivity");
    model.result("pg4").set("data", "cpl1");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Eddy Diffusivity (m<sup>2</sup>/s)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "mueff/spf.rho");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").set("planecoordsys", "cartesian");
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"", "", ""});
    model.result("pg4").feature("surf1").feature("def1").set("descr", "");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("planecoordsys", "cutplane");
    model.result("pg4").feature("surf1").feature("def1")
         .set("expr", new String[]{"0", "max((z-maxop1(z))/(h_ta_i+aveop1(z)-maxop1(z)),0)*(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))*exp(min(0,1-(maxop1(z)-z)/(abs(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))+sqrt(eps))))"});
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("planecoordsys", "cartesian");
    model.result("pg4").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("str1").set("descr", "Velocity field (spatial frame)");
    model.result("pg4").feature("str1").set("planecoordsys", "cutplane");
    model.result("pg4").feature("str1").set("expr", new String[]{"-u*cpl1ny+v*cpl1nx", "v"});
    model.result("pg4").feature("str1").setIndex("expr", "w", 1);
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.02);
    model.result("pg4").feature("str1").set("color", "white");
    model.result("pg4").feature("str1").create("def1", "Deform");
    model.result("pg4").feature("str1").feature("def1").set("planecoordsys", "cartesian");
    model.result("pg4").feature("str1").feature("def1").set("expr", new String[]{"", "", ""});
    model.result("pg4").feature("str1").feature("def1").set("descr", "");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("def1").set("planecoordsys", "cutplane");
    model.result("pg4").feature("str1").feature("def1")
         .set("expr", new String[]{"0", "max((z-maxop1(z))/(h_ta_i+aveop1(z)-maxop1(z)),0)*(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))*exp(min(0,1-(maxop1(z)-z)/(abs(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))+sqrt(eps))))"});
    model.result("pg4").feature("str1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("str1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("planecoordsys", "cartesian");
    model.result("pg4").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("arws1").set("descr", "Velocity field (spatial frame)");
    model.result("pg4").feature("arws1").set("planecoordsys", "cutplane");
    model.result("pg4").feature("arws1").set("expr", new String[]{"-u*cpl1ny+v*cpl1nx", "v"});
    model.result("pg4").feature("arws1").setIndex("expr", "w", 1);
    model.result("pg4").feature("arws1").set("scaleactive", true);
    model.result("pg4").feature("arws1").set("scale", 0.25);
    model.result("pg4").feature("arws1").set("color", "white");
    model.result("pg4").feature("arws1").create("def1", "Deform");
    model.result("pg4").feature("arws1").feature("def1").set("planecoordsys", "cartesian");
    model.result("pg4").feature("arws1").feature("def1").set("expr", new String[]{"", "", ""});
    model.result("pg4").feature("arws1").feature("def1").set("descr", "");
    model.result("pg4").run();
    model.result("pg4").feature("arws1").feature("def1").set("planecoordsys", "cutplane");
    model.result("pg4").feature("arws1").feature("def1")
         .set("expr", new String[]{"0", "max((z-maxop1(z))/(h_ta_i+aveop1(z)-maxop1(z)),0)*(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))*exp(min(0,1-(maxop1(z)-z)/(abs(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))+sqrt(eps))))"});
    model.result("pg4").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("arws1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("Shear Rate");
    model.result("pg5").set("data", "cpl1");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "lg(Shear Rate (1/s))");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "log10(spf.sr)");
    model.result("pg5").feature("surf1").set("recover", "ppr");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").feature("surf1").feature("def1").set("planecoordsys", "cartesian");
    model.result("pg5").feature("surf1").feature("def1").set("expr", new String[]{"", "", ""});
    model.result("pg5").feature("surf1").feature("def1").set("descr", "");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("planecoordsys", "cutplane");
    model.result("pg5").feature("surf1").feature("def1")
         .set("expr", new String[]{"0", "max((z-maxop1(z))/(h_ta_i+aveop1(z)-maxop1(z)),0)*(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))*exp(min(0,1-(maxop1(z)-z)/(abs(max(genext1(spf.etaFS),0)+min(genext1(spf.etaFS),0))+sqrt(eps))))"});
    model.result("pg5").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg5").run();
    model.result().numerical().create("av1", "AvVolume");
    model.result().numerical("av1").label("Radial Mixing Time");
    model.result().numerical("av1").selection().all();
    model.result().numerical("av1").setIndex("expr", "d_ta_i^2/(mueff/spf.rho)", 0);
    model.result().numerical("av1").setIndex("unit", "h", 0);
    model.result().numerical("av1").setIndex("descr", "", 0);
    model.result().numerical("av1").setIndex("descr", "Radial Mixing Time Scale", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Radial Mixing Time");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().numerical().create("av2", "AvVolume");

    return model;
  }

  public static Model run3(Model model) {
    model.result().numerical("av2").label("Axial Mixing Time");
    model.result().numerical("av2").selection().all();
    model.result().numerical("av2").setIndex("expr", "h_ta_i^2/(mueff/spf.rho)", 0);
    model.result().numerical("av2").setIndex("unit", "h", 0);
    model.result().numerical("av2").setIndex("descr", "Axial Mixing Time Scale", 0);
    model.result().numerical("av2").set("table", "tbl1");
    model.result().numerical("av2").appendResult();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "intop_rad_pi3((u*x+v*y)/sqrt(x^2+y^2)*2*pi/Omega)", 0);
    model.result().numerical("gev1").setIndex("unit", "m^3", 0);
    model.result().numerical("gev1").setIndex("descr", "intop_rad_pi3((u*x+v*y)/sqrt(x^2+y^2)*2*pi/Omega)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl2");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "intop_ax_pi3(w*2*pi/Omega)", 0);
    model.result().numerical("gev2").setIndex("unit", "m^3", 0);
    model.result().numerical("gev2").setIndex("descr", "intop_ax_pi3(w*2*pi/Omega)", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Global Evaluation 2");
    model.result().numerical("gev2").set("table", "tbl3");
    model.result().numerical("gev2").setResult();
    model.result().table().create("tbl4", "Table");
    model.result().table().remove("tbl4");
    model.result().table("tbl1").label("Mixing Time Scales");
    model.result("pg1").run();

    model.title(null);

    model.description("");

    model.label("mixer_embedded.mph");

    model.result("pg1").run();

    model.setExpectedComputationTime("8 \u5206\u949f");

    model.component("comp1").geom("geom1").feature("pi3").comments("impeller");
    model.component("comp1").geom("geom1").feature("pi4").comments("impeller");
    model.component("comp1").geom("geom1").feature("pi5").comments("impeller");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///mixer.docx");
    model.result().report("rpt1").set("enumlevel", "none");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u6405\u62cc\u5668");
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includepath", false);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u6405\u62cc\u5668\u51e0\u4f55\u4e0e\u7f51\u683c");
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("\u901f\u5ea6\u573a\u548c\u6d41\u7ebf");
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2").label("\u6da1\u6269\u6563\u7387");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg4");
    model.result().report("rpt1").feature("sec4").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg3").label("\u526a\u5207\u901f\u7387");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("noderef", "pg5");
    model.result().report("rpt1").feature("sec4").feature().create("field1", "DoubleDataField");
    model.result().report("rpt1").feature("sec4").feature("field1")
         .label("\u6df7\u5408\u65f6\u95f4\u5c3a\u5ea6\u548c\u6d41\u6570");
    model.result().report("rpt1").feature("sec4").feature("field1").set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature("field1").set("commentssource", "custom");
    model.result().report("rpt1").feature("sec4").feature("field1")
         .set("comments", "\u6df7\u5408\u65f6\u95f4\u5c3a\u5ea6 (s)");

    model.title("\u6405\u62cc\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u529f\u80fd\u533a\u4e2d\u7684\u591a\u4e2a\u9009\u9879\u5361\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u96f6\u4ef6\u548c\u7d2f\u79ef\u9009\u62e9\u53ef\u7528\u4e8e\u5728\u5d4c\u5165\u6a21\u578b\u4e2d\u81ea\u52a8\u8fdb\u884c\u57df\u548c\u8fb9\u754c\u8bbe\u7f6e\n\u2022 \u6dfb\u52a0\u6216\u79fb\u9664\u5177\u6709\u4e0d\u540c\u51e0\u4f55\u6784\u578b\u7684\u51e0\u4f55\u96f6\u4ef6\n\u2022 \u7528\u4e8e\u521b\u5efa\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u8ba1\u7b97\u5b8c\u6210\u540e\u53d1\u9001\u5e26\u6709\u62a5\u544a\u7684\u7535\u5b50\u90ae\u4ef6\n\u2022 \u7528\u6237\u5b9a\u4e49\u7684\u7535\u5b50\u90ae\u4ef6\u670d\u52a1\u5668\u8bbe\u7f6e\uff0c\u5728\u8fd0\u884c\u5df2\u7f16\u8bd1\u7684\u72ec\u7acb App \u65f6\u975e\u5e38\u6709\u7528\n\u2022 \u7528\u4e8e\u63a7\u5236\u5207\u9762\u56fe\u53ef\u89c6\u5316\u7684\u6ed1\u5757\n\n\u8be5 App \u63d0\u4f9b\u4e00\u4e2a\u7528\u6237\u53cb\u597d\u7684\u754c\u9762\uff0c\u53ef\u4f9b\u79d1\u7814\u4eba\u5458\u548c\u5de5\u827a\u5de5\u7a0b\u5e08\u7814\u7a76\u5bb9\u5668\u3001\u53f6\u8f6e\u3001\u6321\u677f\u548c\u5de5\u4f5c\u6761\u4ef6\u5bf9\u9a71\u52a8\u53f6\u8f6e\u6240\u9700\u7684\u6df7\u5408\u6548\u7387\u548c\u529f\u7387\u7684\u5f71\u54cd\u3002\u60a8\u53ef\u4ee5\u4f7f\u7528\u8be5 App \u6765\u4e86\u89e3\u5e76\u4f18\u5316\u6405\u62cc\u5668\u5728\u7ed9\u5b9a\u6d41\u4f53\u4e0b\u7684\u8bbe\u8ba1\u548c\u5de5\u4f5c\u60c5\u51b5\u3002\n\n\u60a8\u53ef\u4ee5\u4ece\u4e09\u79cd\u7c7b\u578b\u7684\u5217\u8868\u4e2d\u6307\u5b9a\u5bb9\u5668\u7684\u5c3a\u5bf8\uff0c\u5e76\u4ece\u5341\u4e00\u79cd\u7c7b\u578b\u7684\u5217\u8868\u4e2d\u6307\u5b9a\u53f6\u8f6e\u7684\u5c3a\u5bf8\u548c\u6784\u578b\uff1b\u6b64\u5916\uff0c\u5bb9\u5668\u8fd8\u53ef\u4ee5\u914d\u5907\u6321\u677f\u3002\u60a8\u8fd8\u53ef\u4ee5\u8fdb\u4e00\u6b65\u6307\u5b9a\u53f6\u8f6e\u901f\u5ea6\u4ee5\u53ca\u5f85\u6df7\u5408\u6d41\u4f53\u7684\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
