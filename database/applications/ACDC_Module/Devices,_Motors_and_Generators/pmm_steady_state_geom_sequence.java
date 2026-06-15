/*
 * pmm_steady_state_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:20 by COMSOL 6.3.0.290. */
public class pmm_steady_state_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("Np", "10");
    model.param().descr("Np", "Number of poles");
    model.param().set("Ns", "60");
    model.param().descr("Ns", "Number of slots");
    model.param().set("Nsec", "gcd(Np,Ns)");
    model.param().descr("Nsec", "Number of modeled symmetric/antiperiodic sectors");
    model.param().set("stator_core_rout", "120[mm]");
    model.param().descr("stator_core_rout", "Stator core outer radius");
    model.param().set("stator_core_th", "stator_core_rout*0.3");
    model.param().descr("stator_core_th", "Stator core radial thickness");
    model.param().set("stator_core_rin", "stator_core_rout-stator_core_th");
    model.param().descr("stator_core_rin", "Stator core inner radius");
    model.param().set("N_coil_slot", "2");
    model.param().descr("N_coil_slot", "Number of coils per slot");
    model.param().set("airgap", "2[mm]");
    model.param().descr("airgap", "Air gap thickness between stator and rotor");
    model.param().set("slot_pitch_frc", "0.5");
    model.param().descr("slot_pitch_frc", "Stator core slot pitch fraction (0-1)");
    model.param().set("slot_shoe_tip_height", "1[mm]");
    model.param().descr("slot_shoe_tip_height", "Stator slot shoe tip height");
    model.param().set("slot_shoe_angle", "20[deg]");
    model.param().descr("slot_shoe_angle", "Slot shoe angle");
    model.param().set("slot_opening", "2.5[mm]");
    model.param().descr("slot_opening", "Slot opening width");
    model.param().set("slot_width", "stator_core_rin*2*sin(pi/Ns*slot_pitch_frc)");
    model.param().descr("slot_width", "Stator slot width");
    model.param().set("slot_height", "stator_core_th*0.7");
    model.param().descr("slot_height", "Stator slot height");
    model.param()
         .set("slot_shoe_base_height", "slot_shoe_tip_height+(slot_width-slot_opening)/2*tan(slot_shoe_angle)");
    model.param().descr("slot_shoe_base_height", "Stator slot shoe base height");
    model.param().set("coil_height", "(slot_height-slot_shoe_base_height)/N_coil_slot");
    model.param().descr("coil_height", "Coil height");
    model.param().set("rotor_core_rout", "stator_core_rin-airgap");
    model.param().descr("rotor_core_rout", "Rotor core outer radius");
    model.param().set("shaft_rout", "rotor_core_rout*.2");
    model.param().descr("shaft_rout", "Shaft radius");
    model.param().set("rotor_bridge", "1.5[mm]");
    model.param()
         .descr("rotor_bridge", "Rotor iron thickness between airgap and outmost flux barriers/ air pockets");
    model.param().set("pole_bridge", "2[mm]");
    model.param().descr("pole_bridge", "Rotor iron thickness between poles");
    model.param().set("v_bridge", "2[mm]");
    model.param().descr("v_bridge", "Rotor iron thickness between magnets placed in v-shape");
    model.param().set("mag_ang", "20[deg]");
    model.param().descr("mag_ang", "Angle of magnets placed in v-shape");
    model.param().set("magnet_height", "5[mm]");
    model.param().descr("magnet_height", "Magnet height");
    model.param().set("flux_barrier", "2[mm]");
    model.param().descr("flux_barrier", "Width of flux barrier (inversely proportional to magnet width)");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").run("");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c1");
    model.component("comp1").geom("geom1").feature("c1").set("r", "stator_core_rout");
    model.component("comp1").geom("geom1").feature("c1").set("angle", "360/Nsec");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", "stator_core_rin");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "0.95*stator_core_rin", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "slot_opening/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "stator_core_rin+slot_shoe_tip_height", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "slot_opening/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "stator_core_rin+slot_shoe_base_height", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "slot_width/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "stator_core_rin+slot_height", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "slot_width/2", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "stator_core_rin+slot_height", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-slot_width/2", 4, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "stator_core_rin+slot_shoe_base_height", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-slot_width/2", 5, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "stator_core_rin+slot_shoe_tip_height", 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-slot_opening/2", 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "0.95*stator_core_rin", 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-slot_opening/2", 7, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("rot1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("rot1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(360/Ns/2,360/Ns,360/Nsec-360/Ns/2)");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").named("rot1");
    model.component("comp1").geom("geom1").feature("dif2").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"coil_height", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "slot_width", 1);
    model.component("comp1").geom("geom1").feature("r1")
         .set("pos", new String[]{"stator_core_rin+slot_shoe_base_height", "0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("pos", "-slot_width/2", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"N_coil_slot", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"coil_height", "0"});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").named("arr1");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "range(360/Ns/2,360/Ns,360/Nsec-360/Ns/2)");
    model.component("comp1").geom("geom1").feature("rot2").set("selresult", true);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "stator_core_rout");
    model.component("comp1").geom("geom1").feature("c3").set("angle", "360/Nsec");
    model.component("comp1").geom("geom1").feature().duplicate("c4", "c3");
    model.component("comp1").geom("geom1").feature("c4").set("r", "stator_core_rin-airgap/2");
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("c3");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("c4");
    model.component("comp1").geom("geom1").feature("dif3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif3").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"dif2", "rot2", "dif3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.geom()
         .load(new String[]{"part1"}, "ACDC_Module\\Rotating_Machinery_2D\\Rotors\\Internal\\embedded_magnet_v_shape_internal_rotor_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("pi1", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "number_of_modeled_poles", "Np/Nsec");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "shaft_diam", "shaft_rout*2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rotor_diam", "rotor_core_rout*2");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "cont_diam", "rotor_core_rout*2+airgap");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "angle_magnets", "mag_ang");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "magnet_h", "magnet_height");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rotor_bridge_size", "rotor_bridge");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "poles_bridge_size", "pole_bridge");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "v_corner_bridge_size", "v_bridge");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "v_distance", "v_bridge+1[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "flux_barrier_size", "flux_barrier");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "flux_barrier_indent_fraction", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_shaft.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_magnets.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_flux_barriers.dom", true);
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("selkeepdom", "pi1_rotor_solid_domains.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_all.dom", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("disksel1").set("r", "stator_core_rout*1.1");
    model.component("comp1").geom("geom1").feature("disksel1").set("angle1", "-360/Ns/16");
    model.component("comp1").geom("geom1").feature("disksel1").set("angle2", "360/Ns/16");
    model.component("comp1").geom("geom1").feature("disksel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("disksel2", "disksel1");
    model.component("comp1").geom("geom1").feature("disksel2").set("angle1", "-360/Ns/16+360/Nsec");
    model.component("comp1").geom("geom1").feature("disksel2").set("angle2", "360/Ns/16+360/Nsec");
    model.component("comp1").geom("geom1").run("disksel2");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"disksel1", "disksel2"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"dif2", "pi1_rotor_iron"});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("pmm_steady_state_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
